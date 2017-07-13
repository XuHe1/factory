package com.kyx.factory.web.rest;

import com.kyx.factory.dal.domain.DeviceData;
import com.kyx.factory.dal.domain.ProductOrder;
import com.kyx.factory.dal.repository.DeviceDataRepository;
import com.kyx.factory.dal.repository.ProductOrderRepository;
import com.kyx.factory.dal.vo.ProgressVO;
import com.kyx.factory.exception.ErrorEnum;
import com.kyx.factory.exception.GeneralException;
import com.kyx.factory.service.DeviceDataService;
import com.kyx.factory.support.json.JsonResp;
import com.kyx.factory.web.support.BaseResource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/

@Slf4j
@RestController
@RequestMapping("/analyse")
public class AnalyseResource extends BaseResource {
    @Autowired
    private DeviceDataRepository deviceDataRepository;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private DeviceDataService deviceDataService;

    @GetMapping("/order/{orderNo}")
    public ModelAndView list(@PathVariable String orderNo) {


        ModelAndView mv = new ModelAndView();
        mv.setViewName("analyse/index");
        mv.addObject("orderNo", orderNo);

        return mv;
    }

    @GetMapping("/order")
    public JsonResp getOrderByOrderNo(@RequestParam String orderNo) {
        if (StringUtils.isBlank(orderNo)) {
            throw new GeneralException(ErrorEnum.MISS_ORDER_ID);
        }
        orderNo = orderNo.replaceAll("[a-zA-Z]*", "");
        ProductOrder order = productOrderRepository.findOne(new Long(orderNo));
        if (order == null) {
            throw new GeneralException(ErrorEnum.NO_ORDER);
        }
        return ok(order);
    }

    @GetMapping("/progress/order")
    public JsonResp getTestResultByOrder(@RequestParam String orderNo) {
        if (StringUtils.isBlank(orderNo)) {
            throw new GeneralException(ErrorEnum.MISS_ORDER_ID);
        }

        DeviceData deviceData = new DeviceData();
        deviceData.setOrder_id(orderNo);
        deviceData.setTest_result(0);//成功
        Example example = Example.of(deviceData);
        Long finishedCount = deviceDataRepository.count(example);

        deviceData.setTest_result(1); //失败

        Integer[] failedResults = deviceDataRepository.getFaildResult(orderNo);


        Long costTime = 0L;
        if (finishedCount > 0 || failedResults.length > 0) {
            costTime =  deviceDataRepository.findTotalCostTime(orderNo);
        }
        ProgressVO progressVO = new ProgressVO();
        progressVO.setFinishedCount(finishedCount);
        progressVO.setFailedCount((long) failedResults.length);
        progressVO.setFailedResult(failedResults);
        progressVO.setCostTime(costTime);

        return ok(progressVO);
    }



    @GetMapping(path = "/gps/order")
    public JsonResp getGpsStatistic(@RequestParam String orderNo) {
        //数据验证
        if (StringUtils.isBlank(orderNo)) {
            throw new GeneralException(ErrorEnum.MISS_ORDER_ID);
        }

        List<Object[]> gpsCount= deviceDataRepository.getGpsCount(orderNo);
        if (gpsCount == null || gpsCount.size() < 1) {
            throw new GeneralException(ErrorEnum.NO_STATISTIC);
        }
        Map<Object, Object> gpsMap = new HashMap<>();
        for (Object[] gps : gpsCount) {
            gpsMap.put(gps[0], gps[1]);

        }
        return ok(gpsCount);

    }

    @GetMapping(path = "/battery_voltage/order")
    public JsonResp getBatteryVoltageStatistic(@RequestParam String orderNo) {
        //数据验证
        if (StringUtils.isBlank(orderNo)) {
            throw new GeneralException(ErrorEnum.MISS_ORDER_ID);
        }

        Integer[] batteryVoltages= deviceDataRepository.getBatteryVoltage(orderNo);
        if (batteryVoltages == null || batteryVoltages.length == 0) {
            throw new GeneralException(ErrorEnum.NO_STATISTIC);
        }

        return ok(batteryVoltages);

    }

    @GetMapping(path = "/accelerator/order")
    public JsonResp getAcceleratorStatistic(@RequestParam String orderNo) {
        //数据验证
        if (StringUtils.isBlank(orderNo)) {
            throw new GeneralException(ErrorEnum.MISS_ORDER_ID);
        }

        List<Object[]> accelerator= deviceDataRepository.getAccelerator(orderNo);
        if (accelerator == null || accelerator.size() < 1) {
            throw new GeneralException(ErrorEnum.NO_STATISTIC);
        }

        return ok(accelerator);

    }

    @GetMapping(path = "/gyroscope/order")
    public JsonResp getGyroscopeStatistic(@RequestParam String orderNo) {
        //数据验证
        if (StringUtils.isBlank(orderNo)) {
            throw new GeneralException(ErrorEnum.MISS_ORDER_ID);
        }

        List<Object[]> gyroscope= deviceDataRepository.getGyroscope(orderNo);
        if (gyroscope == null || gyroscope.size() < 1) {
            throw new GeneralException(ErrorEnum.NO_STATISTIC);
        }

        return ok(gyroscope);

    }

    @GetMapping(path = "/flash/order")
    public JsonResp getFlashStatistic(@RequestParam String orderNo) {
        //数据验证
        if (StringUtils.isBlank(orderNo)) {
            throw new GeneralException(ErrorEnum.MISS_ORDER_ID);
        }

       Integer[] flashes= deviceDataRepository.getFlash(orderNo);
        if (flashes == null || flashes.length == 0) {
            throw new GeneralException(ErrorEnum.NO_STATISTIC);
        }

        return ok(flashes);

    }

    @GetMapping(path = "/eeprom/order")
    public JsonResp getEepromStatistic(@RequestParam String orderNo) {
        //数据验证
        if (StringUtils.isBlank(orderNo)) {
            throw new GeneralException(ErrorEnum.MISS_ORDER_ID);
        }

        Integer[] eeproms= deviceDataRepository.getEeprom(orderNo);
        if (eeproms == null || eeproms.length == 0) {
            throw new GeneralException(ErrorEnum.NO_STATISTIC);
        }

        return ok(eeproms);

    }

    @GetMapping(path = "/gprs/order")
    public JsonResp getGprsStatistic(@RequestParam String orderNo) {
        //数据验证
        if (StringUtils.isBlank(orderNo)) {
            throw new GeneralException(ErrorEnum.MISS_ORDER_ID);
        }

        Integer[] gprsArray= deviceDataRepository.getGprs(orderNo);
        if (gprsArray == null || gprsArray.length == 0) {
            throw new GeneralException(ErrorEnum.NO_STATISTIC);
        }

        return ok(gprsArray);

    }

    @GetMapping(path = "/can/order")
    public JsonResp getCanStatistic(@RequestParam String orderNo) {
        //数据验证
        if (StringUtils.isBlank(orderNo)) {
            throw new GeneralException(ErrorEnum.MISS_ORDER_ID);
        }

        Integer[] cans= deviceDataRepository.getCans(orderNo);
        if (cans == null || cans.length < 1) {
            throw new GeneralException(ErrorEnum.NO_STATISTIC);
        }

        return ok(cans);

    }

    @GetMapping(path = "/kline/order")
    public JsonResp getKlineStatistic(@RequestParam String orderNo) {
        //数据验证
        if (StringUtils.isBlank(orderNo)) {
            throw new GeneralException(ErrorEnum.MISS_ORDER_ID);
        }

        Integer[] klines= deviceDataRepository.getKlines(orderNo);
        if (klines == null || klines.length < 1) {
            throw new GeneralException(ErrorEnum.NO_STATISTIC);
        }

        return ok(klines);
    }

    @GetMapping(path = "/electric_current/order")
    public JsonResp getElectriccurrentStatistic(@RequestParam String orderNo) {
        //数据验证
        if (StringUtils.isBlank(orderNo)) {
            throw new GeneralException(ErrorEnum.MISS_ORDER_ID);
        }

        Integer[] electricCurrents= deviceDataRepository.getElectriccurrents(orderNo);
        if (electricCurrents == null || electricCurrents.length == 0) {
            throw new GeneralException(ErrorEnum.NO_STATISTIC);
        }

        return ok(electricCurrents);

    }

    @GetMapping("/daily_stat/order")
    public JsonResp getDailyStat(@RequestParam String orderNo) {
        if (StringUtils.isBlank(orderNo)) {
            throw new GeneralException(ErrorEnum.MISS_ORDER_ID);
        }

        List<Object[]> dailyStats = deviceDataRepository.getDailyStat(orderNo);

        if (dailyStats == null || dailyStats.size() < 1) {
            throw new GeneralException(ErrorEnum.NO_STATISTIC);
        }

        return ok(dailyStats);
    }

    @GetMapping("/device_time/order")
    public JsonResp getEveryDeviceTime(@RequestParam String orderNo) {
        if (StringUtils.isBlank(orderNo)) {
            throw new GeneralException(ErrorEnum.MISS_ORDER_ID);
        }
        Long[] deviceCostTimes = deviceDataRepository.findEveryDeviceTime(orderNo);
        if (deviceCostTimes == null || deviceCostTimes.length == 0) {
            throw new GeneralException(ErrorEnum.NO_STATISTIC);
        }

        return ok(deviceCostTimes);

    }

}


