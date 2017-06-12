package com.kyx.factory.web.controller;

import com.kyx.factory.web.support.BaseController;
import com.kyx.factory.web.validation.DeviceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/

@Slf4j
@Controller
@RequestMapping("/web/plan")
public class ProductPlanController extends BaseController {


    @GetMapping
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("config/list");
        mv.addObject("devices", DeviceType.getAllType());
        return mv;
    }

    @GetMapping("/sn")
    public ModelAndView snInitialize() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("config/sn-list");
        mv.addObject("devices", DeviceType.getAllType());
        return mv;
    }

}
