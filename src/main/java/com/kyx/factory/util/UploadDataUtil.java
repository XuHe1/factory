package com.kyx.factory.util;/**
 * Created by XuHe on 17/5/10.
 */

import com.kyx.factory.dal.domain.DeviceData;
import com.kyx.factory.dal.repository.DeviceDataRepository;
import com.kyx.factory.exception.AppFailure;
import com.kyx.factory.exception.Failure;
import com.kyx.factory.exception.Ok;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

/**
 * 上报测试数据到数据库
 *
 * @author h.xu
 * @create 2017-05-10 下午1:50
 **/

public class UploadDataUtil {
    @Autowired
    private  DeviceDataRepository deviceDataRepository;

    private final static Logger logger = LoggerFactory.getLogger(UploadDataUtil.class);
    private static Object lock = new Object();

    public ResponseEntity uploadToDB(DeviceData deviceData){
        synchronized (lock){

            String sn = deviceData.getSn();
            if(deviceData.getTestResult() == 0){
                List<DeviceData> deviceDataList = deviceDataRepository.getBySn(sn);
                if (deviceDataList != null  && deviceDataList.size() > 0){
                    logger.error("" + AppFailure.ALREADY_EXISTS.toString());
                    return new Failure(AppFailure.ALREADY_EXISTS);
                }
            }

            deviceData.setReceiveTime(new Date());
            deviceDataRepository.save(deviceData);
            return new Ok(deviceData);
        }
    }
}
