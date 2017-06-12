package com.kyx.factory.service;

import com.kyx.factory.dal.domain.ProductOrder;
import com.kyx.factory.dal.domain.SnRange;

/**
 * sn号段分发服务
 *
 * @author h.xu
 * @create 2017-06-08 下午6:50
 **/

public interface SnAllocateService {
    void allocate(ProductOrder order, SnRange snRange);
}
