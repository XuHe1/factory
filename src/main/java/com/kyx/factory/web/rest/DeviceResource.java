package com.kyx.factory.web.rest;

import com.kyx.factory.dal.domain.Device;
import com.kyx.factory.dal.repository.DeviceRepository;
import com.kyx.factory.support.db.Page;
import com.kyx.factory.support.json.JsonResp;
import com.kyx.factory.web.model.BootstrapTableDTO;
import com.kyx.factory.web.support.BaseResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangle<thisiswangle@gmail.com>
 */
@RestController
@RequestMapping("/devices")
public class DeviceResource extends BaseResource {
    JsonResp<?> findDevices(@RequestParam(defaultValue = "10") Integer limit,
                            @RequestParam(defaultValue = "0") Integer offset) {

        Integer page = (int)Math.floor(offset / limit);
        Pageable pageable = new PageRequest(page, limit);
        return ok(Page.newPage(deviceRepository.findAll(pageable)));
    }

    @GetMapping("/table")
    BootstrapTableDTO<?> findDeviceTable(@RequestParam(defaultValue = "10") Integer limit,
                                     @RequestParam(defaultValue = "0") Integer offset) {

        Integer page = (int)Math.floor(offset / limit);
        Pageable pageable = new PageRequest(page, limit);

        Page<Device> devicePage = Page.newPage(deviceRepository.findAll(pageable));

        BootstrapTableDTO<Device> table  = new BootstrapTableDTO<>();
        table.setTotal(devicePage.getTotalCount());
        table.setRows(devicePage.getList());
        return table;
    }

    @Autowired
    private DeviceRepository deviceRepository;
}
