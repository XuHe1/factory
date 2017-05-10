package com.kyx.factory.config;

import com.google.common.collect.Maps;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userDetailsAddingInterceptor());
    }

    @Bean
    public HandlerInterceptor userDetailsAddingInterceptor() {
        return new HandlerInterceptorAdapter() {
            private static final String USER_DETAILS_NAME = "userdetails";
            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response,
                                   Object handler, ModelAndView view) {
                if (view == null)
                    return;

                Map<String, String> userDetails = Maps.newHashMap();
                userDetails.put("username", "管理员");
                view.addObject(USER_DETAILS_NAME, userDetails);
            }
        };
    }

    @Bean
    public HandlerInterceptor sidebarAddingInterceptor() {
        return new HandlerInterceptorAdapter() {
            private static final String SIDEBAR_URI_NAME = "_sidebar_uri";
            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response,
                                   Object handler, ModelAndView view) {
                if (view == null)
                    return;

                view.addObject(SIDEBAR_URI_NAME, request.getRequestURI().split("/")[1]);
            }
        };
    }
}
