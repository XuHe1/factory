package com.kyx.factory.web.controller;

import com.kyx.factory.config.WebConfig;
import com.kyx.factory.dal.domain.DeviceData;
import com.kyx.factory.dal.repository.DeviceDataRepository;
import com.kyx.factory.util.DateUtils;
import com.kyx.factory.util.FillDataCallback;
import com.kyx.factory.util.SimpleExcelUtil;
import com.kyx.factory.util.VersionUtil;
import com.kyx.factory.web.support.BaseController;
import com.kyx.factory.web.validation.DeviceType;
import com.kyx.factory.web.validation.FactoryEnum;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/

@Slf4j
@Controller
@RequestMapping("/web/devices")
public class DeviceController extends BaseController {
    @Autowired
    private WebConfig.AppConfig appConfig;

    @Autowired
    private DeviceDataRepository deviceDataRepository;

    @GetMapping
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("device/list");
        mv.addObject("devices", DeviceType.getAllType());
        mv.addObject("factories", FactoryEnum.getAllFactory());
        return mv;
    }

    @RequestMapping("/export")
    public void export( @RequestParam Integer testResult,
                        @RequestParam String factory,
                        @RequestParam String orderNo,
                        HttpServletRequest request, HttpServletResponse response) {

        DeviceData data = new DeviceData();
        if (testResult != null) {
            data.setTest_result(testResult);
        }

        if (!StringUtils.isBlank(factory)) {
            data.setFactory(factory);
        }

        if (!StringUtils.isBlank(orderNo)) {
            data.setOrder_id(orderNo);
        }
        data.setInvalid(0);
        Example<DeviceData> example = Example.of(data);
        List<DeviceData> datas = deviceDataRepository.findAll(Example.of(data));

        String fileName = "product_overview_";
        String dateStr = DateUtils.toNormalDate(new Date());
        fileName += dateStr;
        String titles[] = ("设备, 工厂, 生产线, 硬件版本, 软件版本, 芯片号, 设备号, ICCID, GPS, FLASH, eeprom," +
               " GPRS, 电压, 电流, acceX, acceY, acceZ, gyroX, gyroY, gyroZ, 测试结果, 接收时间").split(",");
        this.generateExcel(datas, fileName, titles, response);

    }


    private void generateExcel(List<DeviceData> datas, String fileName, String[] titles , HttpServletResponse response) {

        SimpleExcelUtil.createExcelWithSingleSheet(fileName, titles, response,
        new FillDataCallback() {
        public void fill(WritableSheet wsheet) {
            try {
                for (int i = 0, j = 1, k = 0; i < datas.size(); i++, j++, k = 0) {
                DeviceData data = datas.get(i);
                wsheet.addCell(new Label(k++, j, data.getDevice().toString()));
                wsheet.addCell(new Label(k++, j, data.getFactory().toString()));
                wsheet.addCell(new Label(k++, j, data.getProduct_line().toString()));
                wsheet.addCell(new Label(k++, j, VersionUtil.getVestion(data.getHw_version()).toString()));
                wsheet.addCell(new Label(k++, j, VersionUtil.getVestion(data.getSw_version()).toString()));
                wsheet.addCell(new Label(k++, j, data.getChip_id().toString()));
                wsheet.addCell(new Label(k++, j, data.getSn() == null ? appConfig.getDefaultSN() : data.getSn().toString()));
                wsheet.addCell(new Label(k++, j, data.getIccid().replaceAll("ICCID:", "").toString()));
                wsheet.addCell(new Label(k++, j, data.getGps_count().toString()));
                wsheet.addCell(new Label(k++, j, data.getFlash().toString()));
                wsheet.addCell(new Label(k++, j, data.getEeprom().toString()));
                wsheet.addCell(new Label(k++, j, data.getGprs().toString()));
                wsheet.addCell(new Label(k++, j, data.getBattery_voltage().toString()));
                wsheet.addCell(new Label(k++, j, data.getElectric_current().toString()));
                wsheet.addCell(new Label(k++, j, data.getAcce_x().toString()));
                wsheet.addCell(new Label(k++, j, data.getAcce_y().toString()));
                wsheet.addCell(new Label(k++, j, data.getAcce_z().toString()));
                wsheet.addCell(new Label(k++, j, data.getGyro_x().toString()));
                wsheet.addCell(new Label(k++, j, data.getGyro_y().toString()));
                wsheet.addCell(new Label(k++, j, data.getAcce_z().toString()));
                wsheet.addCell(new Label(k++, j, data.getTest_result() == 0 ? "通过" : "未通过"));
                wsheet.addCell(new Label(k++, j, data.getReceive_time().toString()));

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
