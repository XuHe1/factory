package com.kyx.factory.web.controller;

import com.kyx.factory.config.WebConfig;
import com.kyx.factory.dal.domain.DeviceData;
import com.kyx.factory.dal.repository.DeviceDataRepository;
import com.kyx.factory.util.DateUtils;
import com.kyx.factory.util.FillDataCallback;
import com.kyx.factory.util.SimpleExcelUtil;
import com.kyx.factory.web.support.BaseController;
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
    public String list() {
        return "device/list";
    }

    @RequestMapping("/export")
    public void export( @RequestParam Integer testResult,
                        @RequestParam String factory,
                        @RequestParam Integer productLine,
                        HttpServletRequest request, HttpServletResponse response) {

        DeviceData data = new DeviceData();
        if (testResult != null) {
            data.setTestResult(testResult);
        }

        if (!StringUtils.isBlank(factory)) {
            data.setFactory(factory);
        }

        if (productLine != null) {
            data.setProductLine(productLine);
        }

        Example<DeviceData> example = Example.of(data);
        List<DeviceData> datas = deviceDataRepository.findAll(Example.of(data));

        String fileName = "product_overview_";
        String dateStr = DateUtils.toNormalDate(new Date());
        fileName += dateStr;
        String titles[] = ("设备, 工厂, 生产线, 硬件版本, 软件版本, 芯片号, 设备号, ICCID, GPS, FLASH, eeprom," +
               " GPRS, 电压, 电流, 测试结果, 接收时间").split(",");
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
                wsheet.addCell(new Label(k++, j, data.getProductLine().toString()));
                wsheet.addCell(new Label(k++, j, data.getHwVersion().toString()));
                wsheet.addCell(new Label(k++, j, data.getSwVersion().toString()));
                wsheet.addCell(new Label(k++, j, data.getChipId().toString()));
                wsheet.addCell(new Label(k++, j, data.getSn() == null ? appConfig.getDefaultSN() : data.getSn().toString()));
                wsheet.addCell(new Label(k++, j, data.getIccid().toString()));
                wsheet.addCell(new Label(k++, j, data.getGpsCount().toString()));
                wsheet.addCell(new Label(k++, j, data.getFlash().toString()));
                wsheet.addCell(new Label(k++, j, data.getEeprom().toString()));
                wsheet.addCell(new Label(k++, j, data.getGprs().toString()));
                wsheet.addCell(new Label(k++, j, data.getElectricCurrent().toString()));
                wsheet.addCell(new Label(k++, j, data.getBatteryVoltage().toString()));
                wsheet.addCell(new Label(k++, j, data.getTestResult() == 0 ? "通过" : "未通过"));
                wsheet.addCell(new Label(k++, j, data.getReceiveTime().toString()));

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
