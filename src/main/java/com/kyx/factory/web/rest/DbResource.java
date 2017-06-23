package com.kyx.factory.web.rest;

import com.kyx.factory.dal.repository.DbRepository;
import com.kyx.factory.support.json.JsonResp;
import com.kyx.factory.web.support.BaseResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 清空测试数据
 *
 * @author h.xu
 * @create 2017-06-23 上午10:26
 **/
@Slf4j
@RestController
@RequestMapping("/db")
public class DbResource extends BaseResource{
    @Autowired
    private DbRepository dbRepository;
    @GetMapping("/flyway")
    public JsonResp getFlyway() {
        List<Object[]> objects = dbRepository.getSchemaVersion();
        return ok(objects);
    }
}
