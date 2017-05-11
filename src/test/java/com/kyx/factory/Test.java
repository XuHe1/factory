package com.kyx.factory;

import com.kyx.factory.web.model.BootstrapTableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * 测试
 *
 * @author h.xu
 * @create 2017-05-10 下午3:56
 **/


public class Test {
    @Autowired
    private TestRestTemplate template = new TestRestTemplate();

    @org.junit.Test
    public void listDeviceData() {

        BootstrapTableDTO obj = template.getForEntity("http://localhost:8080/product/list", BootstrapTableDTO.class).getBody();
        assert(obj.getTotal().intValue() > 0);

    }

    @org.junit.Test
    public void addDeviceData() {
        String url = "http://localhost:8080/product";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("device", "look");
        params.add("factory", "APPLE");
        params.add("productLine","0");
        params.add("testResult", "1");
        params.add("hwVersion", "0x0100");
        params.add("swVersion", "0x0123");
        params.add("chipId", "0x000102030405060708091011");
        params.add("sn", "Z000100");
        params.add("iccid", "898602b0101680011023");
        params.add("gpsCount", "2");
        params.add("flash" , "1");
        params.add("eeprom", "1");
        params.add("gprs", "1");
        params.add("batteryVoltage", "11");
        params.add("electricCurrent", "5");
        params.add("acceX", "-0.5");
        params.add("acceY" , "0.8");
        params.add("acceZ", "-0.6");
        params.add("gyroX", "120");
        params.add("gyroY", "120");
        params.add("gyroZ", "-120");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        String resp =  template.postForEntity(url, request, String.class).getBody();

        assert(resp == null);

    }

    @org.junit.Test
    public void addProductConfig() {
        String url = "http://localhost:8080/product_config";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("device", "look");
        params.add("factory", "APPLES");
        params.add("productLine", "1");
        params.add("startSn", "L001001");
        params.add("snCount", "800");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        String resp =  template.postForEntity(url, request, String.class).getBody();

        assert(resp == null);
    }

}
