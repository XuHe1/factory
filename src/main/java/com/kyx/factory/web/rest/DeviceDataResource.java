package com.kyx.factory.web.rest;

import com.kyx.factory.dal.domain.DeviceData;
import com.kyx.factory.dal.domain.ProductConfig;
import com.kyx.factory.dal.repository.DeviceDataRepository;
import com.kyx.factory.dal.repository.ProductConfigRepository;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
public class DeviceDataResource extends BaseResource {
    @Autowired
    private DeviceDataRepository deviceDataRepository;

    @Autowired
    private ProductConfigRepository productConfigRepository;

    @Autowired
    private DeviceDataService deviceDataService;

    @RequestMapping(path = "/product_config" , method = RequestMethod.POST)
    public JsonResp add(@ModelAttribute ProductConfig productConfig) {
        //数据验证
        String startSn = productConfig.getStartSn();
        Integer snCount = productConfig.getSnCount();
        String numStr = startSn.substring(1,startSn.length());
        Integer startSnNum = Integer.parseInt(numStr);
        Integer endSnNum = startSnNum + snCount -1;
        String factory = productConfig.getFactory();
        Integer productLine = productConfig.getProductLine();
        String device = productConfig.getDevice();


        List<ProductConfig> productConfigList = productConfigRepository.getByDevice(device);

        boolean isCreate = true;
        if(productConfigList != null || productConfigList.size() > 0) {
            Integer left, right;
            for(ProductConfig config : productConfigList){
                if(config.getFactory().equals(factory) && config.getDevice().equals(device) &&
                        config.getProductLine().intValue() == productLine.intValue()) {
                    productConfig.setId(config.getId());
                    productConfig.setCreateTime(config.getCreateTime());
                    productConfig.setUpdateTime(new Date());
                    isCreate = false;
                    continue;
                }
                left = config.getEndSn() - config.getSnCount() + 1;
                right = config.getEndSn();
                if((startSnNum <= right && startSnNum >= left) || (endSnNum <= right && endSnNum >= left) ||
                        (endSnNum >= right && startSnNum <= left) ) {
                    log.error("{}", ErrorEnum.SN_CONFLICT);
                    throw new GeneralException(ErrorEnum.SN_CONFLICT);
                }
            }

        }

        if (isCreate) {
            productConfig.setCreateTime(new Date());
            productConfig.setUpdateTime(new Date());
        }

        productConfig.setEndSn(endSnNum);


        productConfigRepository.saveAndFlush(productConfig);


        return  ok(productConfig);
    }

    @RequestMapping(path = "/product" , method = RequestMethod.POST)
    public JsonResp add(@ModelAttribute DeviceData deviceData) {
         return  deviceDataService.save(deviceData);

    }

    @GetMapping("/product/list")
    BootstrapTableDTO<?> findDeviceTable(@RequestParam(defaultValue = "10") Integer limit,
                                         @RequestParam(defaultValue = "0") Integer offset,
                                         @RequestParam Integer testResult,
                                         @RequestParam String factory,
                                         @RequestParam Integer productLine) {

        BootstrapTableDTO<DeviceData> table  = new BootstrapTableDTO<>();

        Integer page = (int)Math.floor(offset / limit);
        Pageable pageable = new PageRequest(page, limit);
        if(testResult == null && StringUtils.isBlank(factory) &&  productLine == null) {
            Page<DeviceData> devicePage = Page.newPage(deviceDataRepository.findAll(pageable));
            table.setTotal(devicePage.getTotalCount());
            table.setRows(devicePage.getList());
            return table;
        }

        DeviceData data = new DeviceData();
        if (testResult != null) {
            data.setTestResult(testResult);
        }

        if (!StringUtils.isBlank(factory)) {
            data.setFactory(factory);
        }

        if (productLine != null) {
            data.setProductLine(productLine);
        }

        Example<DeviceData> example = Example.of(data);

        org.springframework.data.domain.Page<DeviceData> devicePage = deviceDataRepository.findAll(Example.of(data), pageable);

        table.setTotal(devicePage.getNumberOfElements());
        table.setRows(devicePage.getContent());
        return table;
    }

}


