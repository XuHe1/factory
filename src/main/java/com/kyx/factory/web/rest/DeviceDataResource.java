package com.kyx.factory.web.rest;

import com.kyx.factory.dal.domain.DeviceData;
import com.kyx.factory.dal.repository.DeviceDataRepository;
import com.kyx.factory.exception.ErrorEnum;
import com.kyx.factory.exception.GeneralException;
import com.kyx.factory.service.DeviceDataService;
import com.kyx.factory.support.db.Page;
import com.kyx.factory.support.json.JsonResp;
import com.kyx.factory.web.model.BootstrapTableDTO;
import com.kyx.factory.web.support.BaseResource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/

@Slf4j
@RestController
@RequestMapping("/")
public class DeviceDataResource extends BaseResource {
    @Autowired
    private DeviceDataRepository deviceDataRepository;

    @Autowired
    private DeviceDataService deviceDataService;

    @RequestMapping(path = "/product" , method = RequestMethod.POST)
    public JsonResp add(@Valid  DeviceData deviceData) {
         return  deviceDataService.save(deviceData);

    }

    @RequestMapping(path = "/product" , method = RequestMethod.PUT)
    public JsonResp update(@RequestParam Long id, @RequestParam Integer invalid) {
        log.info("id: {}, invalid: {}", id, invalid);
        if (id == null || invalid == null) {
            log.error("{}", ErrorEnum.PARAM_INVALID);
            throw new GeneralException(ErrorEnum.PARAM_INVALID);
        }
        DeviceData deviceData = new DeviceData();
        deviceData.setId(id);
        deviceData.setInvalid(invalid);
        return  deviceDataService.update(deviceData);

    }

    @GetMapping("/product/list")
    BootstrapTableDTO<?> findDeviceTable(@RequestParam(defaultValue = "10") Integer limit,
                                         @RequestParam(defaultValue = "0") Integer offset,
                                         @RequestParam Integer testResult,
                                         @RequestParam String factory,
                                         @RequestParam String orderNo) {

        BootstrapTableDTO<DeviceData> table  = new BootstrapTableDTO<>();

        Integer page = (int)Math.floor(offset / limit);
        Pageable pageable = new PageRequest(page, limit);
        if(testResult == null && StringUtils.isBlank(factory) &&  StringUtils.isBlank(orderNo)) {
            Page<DeviceData> devicePage = Page.newPage(deviceDataRepository.findAll(pageable));
            table.setTotal(devicePage.getTotalCount());
            table.setRows(devicePage.getList());
            return table;
        }

        DeviceData data = new DeviceData();
        if (testResult != null) {
            data.setTest_result(testResult);
        }

        if (!StringUtils.isBlank(factory)) {
            data.setFactory(factory);
        }

        if (!StringUtils.isBlank(orderNo)) {
            data.setOrder_id(orderNo);
        }

        Example<DeviceData> example = Example.of(data);

        org.springframework.data.domain.Page<DeviceData> devicePage = deviceDataRepository.findAll(Example.of(data), pageable);

        table.setTotal(new Long(devicePage.getTotalElements()).intValue());
        table.setRows(devicePage.getContent());
        return table;
    }

}


