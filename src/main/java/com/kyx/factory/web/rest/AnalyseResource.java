package com.kyx.factory.web.rest;

import com.kyx.factory.dal.domain.ProductOrder;
import com.kyx.factory.dal.repository.DeviceDataRepository;
import com.kyx.factory.dal.repository.ProductOrderRepository;
import com.kyx.factory.service.DeviceDataService;
import com.kyx.factory.support.json.JsonResp;
import com.kyx.factory.web.support.BaseResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(path = "/order/gps" , method = RequestMethod.POST)
    public JsonResp add(@RequestParam String product_order_id, @RequestParam String factory) {
        //数据验证
        String orderIdStr = product_order_id.replaceAll("[a-zA-Z]*", "");

        ProductOrder order = productOrderRepository.findOne( new Long(orderIdStr));
        return ok(order);

    }


}


