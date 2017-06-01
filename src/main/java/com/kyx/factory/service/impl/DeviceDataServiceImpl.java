package com.kyx.factory.service.impl;

import com.kyx.factory.dal.domain.DeviceData;
import com.kyx.factory.dal.repository.DeviceDataRepository;
import com.kyx.factory.exception.ErrorEnum;
import com.kyx.factory.exception.GeneralException;
import com.kyx.factory.service.DeviceDataService;
import com.kyx.factory.support.json.JsonResp;
import com.kyx.factory.support.json.Ok;
import com.kyx.factory.web.validation.DeviceType;
import com.kyx.factory.web.validation.FactoryEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 生产数据上报服务
 *
 * @author h.xu
 * @create 2017-05-10 下午3:15
 **/
@Slf4j
@Service
public class DeviceDataServiceImpl implements DeviceDataService {

    @Autowired
    private DeviceDataRepository deviceDataRepository;

    @Override
    public JsonResp<?> save(DeviceData deviceData) {
        String factory = deviceData.getFactory();
        Integer productLine = deviceData.getProductLine();

        if(!FactoryEnum.getAllFactory().contains(factory)) {
            log.error("{}", ErrorEnum.FACTORY_NOT_EXISTS);
            throw new GeneralException(ErrorEnum.FACTORY_NOT_EXISTS);
        }

        if(productLine > FactoryEnum.getLineCountByFactory(factory) - 1) {
            log.error("{}", ErrorEnum.PRODUCT_LINE_NOT_EXISTS);
            throw new GeneralException(ErrorEnum.PRODUCT_LINE_NOT_EXISTS);
        }

        String device = deviceData.getDevice();
        if(!DeviceType.getAllType().contains(device)) {
            log.error("{}", ErrorEnum.DEVICE_NOT_SUPPORT);
            throw new GeneralException(ErrorEnum.DEVICE_NOT_SUPPORT);
        }

        String sn = deviceData.getSn();
        if(deviceData.getTestResult() == 0) {
            if(!DeviceType.getSnPrefix(device).equals(sn.substring(0,1))) {
                log.error("{}", ErrorEnum.SN_SUFFIX_ERROR);
                throw new GeneralException(ErrorEnum.SN_SUFFIX_ERROR);
            }
            List<DeviceData> deviceDataList = deviceDataRepository.getBySn(sn);
            if (deviceDataList != null  && deviceDataList.size() > 0) {
                log.error("{}", ErrorEnum.ALREADY_EXISTS);
                throw new GeneralException(ErrorEnum.ALREADY_EXISTS);
            }
        }else {
            deviceData.setSn(null);
        }

        deviceData.setReceiveTime(new Date());
        deviceDataRepository.save(deviceData);
        return new Ok<>(deviceData);
    }

    @Override
    public JsonResp update(DeviceData deviceData) {
        DeviceData data = deviceDataRepository.findOne(deviceData.getId());
        if (data == null) {
            log.error("{}", ErrorEnum.NOT_EXISTS);
            throw new GeneralException(ErrorEnum.NOT_EXISTS);
        }
        data.setInvalid(deviceData.getInvalid());
        deviceDataRepository.save(data);
        return new Ok<>(data);
    }
}
