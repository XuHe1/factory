package com.kyx.factory.web.rest;

import com.kyx.factory.dal.domain.Attachment;
import com.kyx.factory.dal.domain.Constant;
import com.kyx.factory.dal.domain.ProductOrder;
import com.kyx.factory.dal.domain.SnInitialize;
import com.kyx.factory.dal.domain.SnRange;
import com.kyx.factory.dal.repository.AttachmentRepository;
import com.kyx.factory.dal.repository.ProductOrderRepository;
import com.kyx.factory.dal.repository.SnInitializeRepository;
import com.kyx.factory.dal.repository.SnRangeRepository;
import com.kyx.factory.exception.ErrorEnum;
import com.kyx.factory.exception.GeneralException;
import com.kyx.factory.service.SnAllocateService;
import com.kyx.factory.support.db.Page;
import com.kyx.factory.support.json.JsonResp;
import com.kyx.factory.support.json.Ok;
import com.kyx.factory.util.EncryptUtil;
import com.kyx.factory.util.VersionUtil;
import com.kyx.factory.web.model.BootstrapTableDTO;
import com.kyx.factory.web.support.BaseResource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/

@Slf4j
@RestController
@RequestMapping("/")
public class ProductOrderResource extends BaseResource {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private SnInitializeRepository snInitializeRepository;

    @Autowired
    private SnRangeRepository snRangeRepository;

    @Autowired
    private SnAllocateService snAllocateService;

    @PostMapping("/order")
    public JsonResp save(@Valid ProductOrder order) {
        /**
         * 1. 查询订单表，quantity--> startsn
         * 2. 订单表为空，查询全局start_sn表
         * 3. start_sn表为空，抛异常
         */
        if (order.getQuantity() < order.getDeliveryCount()) {
            throw new GeneralException(ErrorEnum.DELIVERY_COUNT_OVER);
        }

        Integer startSn = 0;
        Date now = new Date();
        if (order.getId() == null) {
            ProductOrder latestOrder = productOrderRepository.findTopByDevice(order.getDevice(), new Sort(Sort.Direction.DESC, "id"));
            if (latestOrder == null) {
                List<SnInitialize> snInitializeList = snInitializeRepository.getByDevice(order.getDevice());
                if (snInitializeList == null || snInitializeList.size() < 1) {
                    throw new GeneralException(ErrorEnum.NO_START_SN);
                }
                SnInitialize snInitialize = snInitializeList.get(0);
                startSn = snInitialize.getStartSn();

            } else {
                startSn = latestOrder.getEndSn() + 1;
            }

            order.setCreateTime(now);

        } else {
            ProductOrder oldOrder = productOrderRepository.findOne(order.getId());
            if (oldOrder == null) {
                throw new GeneralException(ErrorEnum.NO_ORDER);
            }

            int orderState = oldOrder.getState();
            if (Constant.PO_CREATED != orderState) {
                throw new GeneralException(ErrorEnum.OPERATION_INVALID);
            }

            if (oldOrder.getQuantity() < order.getQuantity()) {
                throw new GeneralException(ErrorEnum.QUANTITY_CAN_NOT_INCREASE);
            }
            order.setCreateTime(oldOrder.getCreateTime());
            startSn = oldOrder.getStartSn();

        }

        order.setStartSn(startSn);
        order.setSnIndex(startSn);
        order.setEndSn(startSn + order.getQuantity() - 1);
        order.setUpdateTime(now);
        order.setState(Constant.PO_CREATED);
        String hwVersion = order.getHwVersion().replace(",", ".");
        order.setHwVersion(hwVersion);
        productOrderRepository.save(order);
        return new Ok(order);



    }

    @GetMapping("/order/user/{username}")
    public JsonResp getByUser(@PathVariable String username) {

        ProductOrder order = new ProductOrder();
        order.setUsername(username);
        Example<ProductOrder> example = Example.of(order);
        long count = productOrderRepository.count(example);
        if (count > 0) {
            throw new GeneralException(ErrorEnum.USER_ALREADY_EXISTS);
        }
        return new Ok(order);
    }

    @GetMapping("/order/{id}")
    public JsonResp get(@PathVariable Long id) {

        ProductOrder order = productOrderRepository.getOne(id);
        if (order == null) {
            throw new GeneralException(ErrorEnum.NOT_EXISTS);
        }
        return new Ok(order);

    }

    @RequestMapping(path = "/order" , method = RequestMethod.PUT)
    public JsonResp update(@RequestParam(value = "id[]") Long[] id, @RequestParam Integer state) {
        log.info("id: {}, state: {}", id, state);
        if (id == null || state == null) {
            log.error("{}", ErrorEnum.PARAM_INVALID);
            throw new GeneralException(ErrorEnum.PARAM_INVALID);
        }
        ProductOrder order = productOrderRepository.findOne(id[0]);
        if (order == null) {
            throw new GeneralException(ErrorEnum.NO_ORDER);
        }
        int orderState = order.getState();
        if (state == orderState) {
            throw new GeneralException(ErrorEnum.OPERATION_INVALID);
        }
        order.setState(state);
        order.setUpdateTime(new Date());
        productOrderRepository.saveAndFlush(order);
        return Ok.newOk(order);



    }

    @GetMapping("/order/list")
    BootstrapTableDTO<?> findOrderTable(@RequestParam(defaultValue = "10") Integer limit,
                                         @RequestParam(defaultValue = "0") Integer offset,
                                         @RequestParam String device,
                                         @RequestParam String factory,
                                         @RequestParam Integer state,
                                         @RequestParam String orderNo) {

        BootstrapTableDTO<ProductOrder> table  = new BootstrapTableDTO<>();

        Integer page = (int)Math.floor(offset / limit);
        Pageable pageable = new PageRequest(page, limit);

        if(StringUtils.isBlank(device) && StringUtils.isBlank(factory) && state == null &&  StringUtils.isBlank(orderNo)) {
            Page<ProductOrder> orderPage = Page.newPage(productOrderRepository.findAll(pageable));
            table.setTotal(orderPage.getTotalCount());
            table.setRows(orderPage.getList());
            return table;
        }

        ProductOrder order = new ProductOrder();
        if (!StringUtils.isBlank(device)) {
            order.setDevice(device);
        }

        if (!StringUtils.isBlank(factory)) {
            order.setFactory(factory);
        }

        if (state != null) {
            order.setState(state);
        }

        if (!StringUtils.isBlank(orderNo)) {
            orderNo = orderNo.replaceAll("[a-zA-Z]*", "");
            order.setId(new Long(orderNo));
        }

        Example<ProductOrder> example = Example.of(order);

        org.springframework.data.domain.Page<ProductOrder> orderPage = productOrderRepository.findAll(Example.of(order), pageable);

        table.setTotal(new Long(orderPage.getTotalElements()).intValue());
        table.setRows(orderPage.getContent());
        return table;
    }

    @PostMapping("/order/user")
    public JsonResp getOrderInfo(HttpServletRequest request, @RequestParam String user_name, @RequestParam String api_key) {

        List<ProductOrder> orderList = productOrderRepository.getByUsername(user_name);
        if (orderList == null || orderList.size() < 1) {
            throw new GeneralException(ErrorEnum.USER_NAME_ERROR);
        }

        ProductOrder order = orderList.get(0);
        StringBuffer url = new StringBuffer("http://factory.ms.getqood.com/user/login?username=");
        url.append(user_name);
        url.append("&password=");
        url.append(order.getPassword());
        String encryptUrl = EncryptUtil.hmacSHA1Encrypt(url.toString(), EncryptUtil.SECRET_KEY);
        if (!encryptUrl.equals(api_key)) {
            throw new GeneralException(ErrorEnum.PASSWORD_ERROR);
        }

        order.setPassword(null);

        StringBuffer sb = new StringBuffer(order.getOrderNoPrefix());
        Long orderId = order.getId();
        String orderIdStr = String.valueOf(orderId);
        for (int i = 0; i < 6 - orderIdStr.length(); i ++) {
            sb.append("0");
        }
        sb.append(orderIdStr);
        order.setOrderNo(sb.toString());
        // 版本号转换， 1.0 ＝> 0x0100  => 256
        order.setHwVersion(VersionUtil.transformVestion(order.getHwVersion()));
        order.setFwVersion(VersionUtil.transformVestion(order.getFwVersion()));

        return new Ok(order);
    }

    @PostMapping("/sn_range/user")
    public JsonResp login(HttpServletRequest request, @RequestParam String user_name, @RequestParam String api_key) {

        List<ProductOrder> orderList = productOrderRepository.getByUsername(user_name);
        if (orderList == null || orderList.size() < 1) {
            throw new GeneralException(ErrorEnum.USER_NAME_ERROR);
        }

        ProductOrder order = orderList.get(0);
        StringBuffer url = new StringBuffer("http://factory.ms.getqood.com/user/login?username=");
        url.append(user_name);
        url.append("&password=");
        url.append(order.getPassword());
        String encryptUrl = EncryptUtil.hmacSHA1Encrypt(url.toString(), EncryptUtil.SECRET_KEY);
        if (!encryptUrl.equals(api_key)) {
            throw new GeneralException(ErrorEnum.PASSWORD_ERROR);
        }

        int state = order.getState();
        if (state == Constant.PO_STOPED) {
            throw new GeneralException(ErrorEnum.ORDER_STOP);
        }

        //重发 nginx do not support "request_id"
        String requestId = request.getHeader("request-id");

        log.info("requestId: {}", requestId);
        if (requestId == null || "".equals(requestId)) {
            List<SnRange> snRangeList = snRangeRepository.findAll();
            return new Ok(snRangeList);
        }

        SnRange snRangeTemp = snRangeRepository.findOne(requestId);
        if (snRangeTemp != null) {
            return new Ok(snRangeTemp);
        }

        SnRange snRange = new SnRange();
        snRange.setRequest_id(requestId);
        snAllocateService.allocate(order, snRange);
        return new Ok(snRange);
    }


    @GetMapping("/file/{attachment_id}")
    public JsonResp serveFile(@PathVariable Long attachment_id) {
        Attachment attachment = attachmentRepository.findOne(attachment_id);
        return new Ok(attachment);
    }

    @RequestMapping(path = "/order", method = RequestMethod.DELETE)
    public void deleteAll() {
        productOrderRepository.deleteAll();
    }


}


