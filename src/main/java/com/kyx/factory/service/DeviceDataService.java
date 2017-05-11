package com.kyx.factory.service;

import com.kyx.factory.dal.domain.DeviceData;
import com.kyx.factory.support.json.JsonResp;

/**
 * 生产数据上报服务
 *
 * @author h.xu
 * @create 2017-05-10 下午3:15
 **/

public interface DeviceDataService {
    JsonResp<?> save(DeviceData deviceData);
}
