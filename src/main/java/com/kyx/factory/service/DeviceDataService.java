package com.kyx.factory.service;/**
 * Created by XuHe on 17/5/10.
 */

import com.kyx.factory.dal.domain.DeviceData;
import org.springframework.http.ResponseEntity;

/**
 * 生产数据上报服务
 *
 * @author h.xu
 * @create 2017-05-10 下午3:15
 **/

public interface DeviceDataService {
    ResponseEntity save(DeviceData deviceData);
}
