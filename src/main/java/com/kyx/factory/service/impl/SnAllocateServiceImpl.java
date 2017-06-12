package com.kyx.factory.service.impl;

import com.kyx.factory.dal.domain.Constant;
import com.kyx.factory.dal.domain.ProductOrder;
import com.kyx.factory.dal.domain.SnRange;
import com.kyx.factory.dal.repository.ProductOrderRepository;
import com.kyx.factory.dal.repository.SnRangeRepository;
import com.kyx.factory.exception.ErrorEnum;
import com.kyx.factory.exception.GeneralException;
import com.kyx.factory.service.SnAllocateService;
import com.kyx.factory.web.validation.DeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author h.xu
 * @create 2017-06-08 下午6:52
 **/
@Service
public class SnAllocateServiceImpl implements SnAllocateService {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private SnRangeRepository snRangeRepository;

    @Override
    @Transactional
    public void allocate(ProductOrder order, SnRange snRange) {

        String device = order.getDevice();
        String snPrefix =  DeviceType.getSnPrefix(device);

        // 分配sn
        order.setState(Constant.PO_STARTED); //已经开始
        Integer snIndex = order.getSnIndex();
        Integer snEnd = order.getEndSn();
        Integer deliveryCount = order.getDeliveryCount();

        Integer newSnIndex = snIndex + deliveryCount;
        order.setSnIndex(snIndex + deliveryCount);

        snRange.setStart_sn(snPrefix + snIndex);
        snRange.setEnd_sn(snPrefix + (newSnIndex - 1));

        snRange.setEnd_sn(snPrefix + (order.getSnIndex() - 1));
        if (snIndex + deliveryCount - 1 > snEnd) {
            order.setSnIndex(snEnd);
            snRange.setEnd_sn(snPrefix + snEnd);
        }

        if (snIndex >= snEnd){
            throw new GeneralException(ErrorEnum.NO_SN_AVAILABLE);
        }

        String startSn = snRange.getStart_sn();
        StringBuffer sb = new StringBuffer(snPrefix);
        for (int i = 0; i < 7 - startSn.length(); i ++) {
            sb.append("0");
        }
        startSn = startSn.replace(snPrefix, sb.toString());
        snRange.setStart_sn(startSn);

        sb = new StringBuffer(snPrefix);
        String endSn = snRange.getEnd_sn();
        for (int i = 0; i < 7 - endSn.length(); i ++) {
            sb.append("0");
        }
        endSn = endSn.replace(snPrefix, sb.toString());
        snRange.setEnd_sn(endSn);
        productOrderRepository.save(order);
        snRangeRepository.save(snRange);

    }
}
