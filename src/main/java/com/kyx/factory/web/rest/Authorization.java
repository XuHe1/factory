package com.kyx.factory.web.rest;

import com.kyx.factory.web.support.BaseResource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证服务
 *
 * @author h.xu
 * @create 2017-06-23 下午7:10
 **/
@Slf4j
@RestController
@RequestMapping("/auth")
public class Authorization  extends BaseResource{

    @GetMapping("/nonce")
    public String getNonce() {
        String randomStr = RandomStringUtils.random(38, true, true);
        long timestamp = System.currentTimeMillis()/1000L;
        randomStr = randomStr + timestamp;
        return randomStr;
    }

}
