package com.kyx.factory.web.controller;

import com.kyx.factory.web.support.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangle<thisiswangle@gmail.com>
 */
@Slf4j
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {
    @GetMapping
    public String index() {
        return "redirect:/web/devices";
    }
}
