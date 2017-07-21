package com.kyx.factory.web.rest;

import com.kyx.factory.dal.domain.ProductOrder;
import com.kyx.factory.dal.domain.UserToken;
import com.kyx.factory.dal.repository.ProductOrderRepository;
import com.kyx.factory.dal.repository.UserTokenRepository;
import com.kyx.factory.exception.ErrorEnum;
import com.kyx.factory.exception.GeneralException;
import com.kyx.factory.support.json.JsonResp;
import com.kyx.factory.util.DateUtils;
import com.kyx.factory.util.EncryptUtil;
import com.kyx.factory.web.support.BaseResource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

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

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private UserTokenRepository userTokenRepository;
    @GetMapping("/nonce")
    public String getNonce() {
        String randomStr = RandomStringUtils.random(38, true, true);
        long timestamp = System.currentTimeMillis()/1000L;
        randomStr = randomStr + timestamp;
        return randomStr;
    }

    @PostMapping("/login")
    public JsonResp login(@RequestParam String api_key,
                          @RequestParam String user_name,
                          @RequestParam String nonce,
                          @RequestParam long time_stamp) {
        //1. 判断timestamp是否超时
        long current = System.currentTimeMillis();
        if (current - time_stamp > 86400000) {
            throw new GeneralException(ErrorEnum.TIMESTAMP_INVALID);
        }

        //2. 判断username+nonce+timestamp是否存在
        List<UserToken> userTokens = userTokenRepository.findByToken(nonce + time_stamp);
        if (userTokens != null && userTokens.size() > 0) {
            throw new GeneralException(ErrorEnum.BAD_REQUEST);
        }

        //3. 验证密码
        List<ProductOrder> orderList = productOrderRepository.getByUsername(user_name);
        if (orderList == null || orderList.size() < 1) {
            throw new GeneralException(ErrorEnum.USER_NAME_ERROR);
        }

        ProductOrder order = orderList.get(0);
        StringBuffer url = new StringBuffer("http://factory.ms.getqood.com/user/login?username=");
        url.append(user_name);
        url.append("&password=");
        url.append(order.getPassword());
        url.append("&nonce=");
        url.append(nonce);
        url.append("&timestamp=");
        url.append(time_stamp);
        String encryptUrl = EncryptUtil.hmacSHA1Encrypt(url.toString(), EncryptUtil.SECRET_KEY);
        if (!encryptUrl.equals(api_key)) {
            throw new GeneralException(ErrorEnum.PASSWORD_ERROR);
        }
        //4. 认证通过，保存username+nonce+timestamp
        UserToken userToken = new UserToken();
        userToken.setUsername(user_name);
        userToken.setToken(nonce + time_stamp);
        userToken.setCreateTime(new Date());
        userToken.setExpireDate(DateUtils.fromUnixtimestamp(System.currentTimeMillis()/1000 + 86400));
        userTokenRepository.save(userToken);

        return ok(order);
    }



    @GetMapping("/token")
    public JsonResp getToken() {
        String randomStr = RandomStringUtils.random(20, true, true);
        long timestamp = System.currentTimeMillis();
        randomStr = randomStr + timestamp;
        // save to db, vailid time 1 day
        return ok(randomStr);
    }





}
