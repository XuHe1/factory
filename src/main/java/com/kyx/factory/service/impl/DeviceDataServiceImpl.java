package com.kyx.factory.service.impl;/**
 * Created by XuHe on 17/5/10.
 */

import com.kyx.factory.dal.domain.DeviceData;
import com.kyx.factory.dal.repository.DeviceDataRepository;
import com.kyx.factory.exception.AppFailure;
import com.kyx.factory.exception.Failure;
import com.kyx.factory.exception.Ok;
import com.kyx.factory.service.DeviceDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    private static Object lock = new Object();

    @Override
    public ResponseEntity save(DeviceData deviceData) {

        synchronized (lock) {
            String sn = deviceData.getSn();
            if(deviceData.getTestResult() == 0) {
                List<DeviceData> deviceDataList = deviceDataRepository.getBySn(sn);
                if (deviceDataList != null  && deviceDataList.size() > 0) {
                    log.error("{}", AppFailure.ALREADY_EXISTS.toString());
                    return new Failure(AppFailure.ALREADY_EXISTS);
                }
            }else {
                deviceData.setSn(null);
            }

            deviceData.setReceiveTime(new Date());
            deviceDataRepository.save(deviceData);
            return new Ok(deviceData);
        }
    }
}
