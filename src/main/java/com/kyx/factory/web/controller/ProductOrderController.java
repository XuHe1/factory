package com.kyx.factory.web.controller;

import com.kyx.factory.config.WebConfig;
import com.kyx.factory.dal.domain.Attachment;
import com.kyx.factory.dal.domain.ProductOrder;
import com.kyx.factory.dal.domain.Project;
import com.kyx.factory.dal.domain.ProjectVersion;
import com.kyx.factory.dal.domain.ReturnData;
import com.kyx.factory.dal.repository.AttachmentRepository;
import com.kyx.factory.dal.repository.ProductOrderRepository;
import com.kyx.factory.dal.repository.SnInitializeRepository;
import com.kyx.factory.exception.ErrorEnum;
import com.kyx.factory.exception.GeneralException;
import com.kyx.factory.support.json.JsonResp;
import com.kyx.factory.support.json.Ok;
import com.kyx.factory.util.DateUtils;
import com.kyx.factory.util.FillDataCallback;
import com.kyx.factory.util.SimpleExcelUtil;
import com.kyx.factory.web.support.BaseController;
import com.kyx.factory.web.validation.DeviceType;
import com.kyx.factory.web.validation.FactoryEnum;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/

@Slf4j
@Controller
@RequestMapping("/web/order")
public class ProductOrderController extends BaseController {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private SnInitializeRepository snInitializeRepository;

    @Autowired
    private WebConfig.AppConfig appConfig;

    @GetMapping
    public ModelAndView list() {

        String url = appConfig.getFwProjectUrl();
        RestTemplate template = new RestTemplate();
        List<Project> projectList = null;
        try {
            projectList =  template.getForEntity(url, List.class).getBody();
        } catch (Exception e) {
            log.warn("{}", e);
            throw new GeneralException(ErrorEnum.FIRMWARE_GET_ERROR);
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("order/list");
        mv.addObject("devices", DeviceType.getAllType());
        mv.addObject("factories", FactoryEnum.getAllFactory());
        mv.addObject("projects", projectList);
        mv.addObject("admin", appConfig.getAdmin());
        return mv;
    }

    @GetMapping("/fm_versions/{project_id}")
    public JsonResp getVersionsByProj(@PathVariable Long project_id) {
        if (project_id == null) {
            throw new GeneralException(ErrorEnum.PARAM_INVALID);
        }
        String url = appConfig.getFwVersionUrl().replace("project_id", String.valueOf(project_id));
        RestTemplate template = new RestTemplate();
        List<ProjectVersion> versionList = null;
        try {
            versionList =  template.getForEntity(url, ReturnData.class).getBody().getData();
        } catch (Exception e) {
            log.warn("{}", e);
            throw new GeneralException(ErrorEnum.FIRMWARE_VERSION_GET_ERROR);
        }

        return  new Ok<>(versionList);
    }

    @PostMapping("/upload")
    public JsonResp handleFileUpload(@RequestParam("file") MultipartFile file) {
        log.info("========={}, {}, {}", file.getOriginalFilename(), file.getContentType(), file.getSize());
        Attachment attachment = new Attachment();
        try {
            attachment.setFileName(file.getOriginalFilename());
            attachment.setContent(file.getBytes());
            attachment.setCreateTime(new Date());
            attachment.setUpdateTime(new Date());
            attachmentRepository.save(attachment);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  new Ok<>(attachment);
    }

    @GetMapping("/attachment/{attachment_id}")
    public void download(@PathVariable Long attachment_id, HttpServletResponse response) {
        System.out.println("********** Download File : ************");
        Attachment attachment = attachmentRepository.findOne(attachment_id);
        byte[] content = attachment.getContent();
       // File file = new File("test.xls");

        try {
            String fileName = new String(attachment.getFileName().getBytes("UTF-8"), "ISO-8859-1");
            // FileUtils.writeByteArrayToFile(file, content);
            OutputStream out = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=\"" + fileName +"\"");
            //response.setContentType("application/msexcel");
            response.setContentType("application/octet-stream");
            out.write(content);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/files/{attachment_id}")
    public ResponseEntity<Resource> serveFile(@PathVariable String attachment_id) {
        System.out.println("\n********** Download File : ************\n");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));

        File file = FileUtils.getFile("/Users/kaiitsugyou/Desktop/2017年4月新车及修订数据--快逸行2017.5.12.xlsx");

        FileSystemResource fileSystemResource = new FileSystemResource(file);

        return new ResponseEntity<>(fileSystemResource, headers, HttpStatus.OK);
       //
    }


    @RequestMapping("/export")
    public void export(@RequestParam String device,
                       @RequestParam String factory,
                       @RequestParam Integer state,
                       @RequestParam String orderNo,
                       HttpServletRequest request, HttpServletResponse response) {

        ProductOrder order = new ProductOrder();
        if (!StringUtils.isBlank(device)) {
            order.setDevice(device);
        }

        if (!StringUtils.isBlank(factory)) {
            order.setFactory(factory);
        }

        if (state != null) {
            order.setState(state);
        }

        if (!StringUtils.isBlank(orderNo)) {
            orderNo = orderNo.replaceAll("[a-zA-Z]*", "");
            order.setId(new Long(orderNo));
        }

        Example<ProductOrder> example = Example.of(order);

        List<ProductOrder> datas = productOrderRepository.findAll(example);

        String fileName = "order_overview_";
        String dateStr = DateUtils.toNormalDate(new Date());
        fileName += dateStr;
        String titles[] = ("订单号, 工厂, 设备, 硬件版本, 数量, 分配sn数/次, 起始sn, 结束sn, 账号, 密码, 预计开始, 预计结束, 创建时间," +
                " 更新时间, 订单状态").split(",");
        this.generateExcel(datas, fileName, titles, response);

    }


    private void generateExcel(List<ProductOrder> datas, String fileName, String[] titles , HttpServletResponse response) {

        SimpleExcelUtil.createExcelWithSingleSheet(fileName, titles, response,
                new FillDataCallback() {
                    public void fill(WritableSheet wsheet) {
                        try {
                            for (int i = 0, j = 1, k = 0; i < datas.size(); i++, j++, k = 0) {
                                ProductOrder data = datas.get(i);
                                wsheet.addCell(new Label(k++, j, (data.getOrderNoPrefix() + data.getId()).toString()));
                                wsheet.addCell(new Label(k++, j, data.getFactory().toString()));
                                wsheet.addCell(new Label(k++, j, data.getDevice().toString()));
                                wsheet.addCell(new Label(k++, j, data.getHwVersion().toString()));
                                wsheet.addCell(new Label(k++, j, data.getQuantity().toString()));
                                wsheet.addCell(new Label(k++, j, data.getDeliveryCount().toString()));
                                wsheet.addCell(new Label(k++, j, data.getStartSn().toString()));
                                wsheet.addCell(new Label(k++, j, data.getEndSn().toString()));
                                wsheet.addCell(new Label(k++, j, data.getUsername().toString()));
                                wsheet.addCell(new Label(k++, j, data.getPassword().toString()));
                                wsheet.addCell(new Label(k++, j, data.getScheduleBegin() == null ? "" : data.getScheduleBegin().toString()));
                                wsheet.addCell(new Label(k++, j, data.getScheduleFinish() == null? "": data.getScheduleFinish().toString()));
                                wsheet.addCell(new Label(k++, j, data.getCreateTime().toString()));
                                wsheet.addCell(new Label(k++, j, data.getUpdateTime().toString()));
                                wsheet.addCell(new Label(k++, j, data.getState() == 0 ? "未开始" : ""));

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    public void fill(WritableWorkbook wbook) {
                    }
                });

    }

}
