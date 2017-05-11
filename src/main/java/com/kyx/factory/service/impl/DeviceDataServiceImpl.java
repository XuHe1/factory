package com.kyx.factory.service.impl;

import com.kyx.factory.dal.domain.DeviceData;
import com.kyx.factory.dal.repository.DeviceDataRepository;
import com.kyx.factory.exception.ErrorEnum;
import com.kyx.factory.exception.GeneralException;
import com.kyx.factory.service.DeviceDataService;
import com.kyx.factory.support.json.JsonResp;
import com.kyx.factory.support.json.Ok;
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

            String sn = deviceData.getSn();
            if(deviceData.getTestResult() == 0) {
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
}
