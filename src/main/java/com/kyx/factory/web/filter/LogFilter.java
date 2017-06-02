package com.kyx.factory.web.filter;

import com.kyx.factory.dal.domain.Log;
import com.kyx.factory.dal.repository.LogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * operations log
 *
 * @author h.xu
 * @create 2017-05-31 下午4:04
 **/
@Slf4j
@WebFilter(urlPatterns = {"/product/*", "/product_config/*"})
public class LogFilter implements Filter {
    @Autowired
    private LogRepository logRepository;

    private static final Map<String, String>  needLogUrls = new HashMap<>();
    static {
        needLogUrls.put("/product", "");

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        log.info("============{}", url);
        if (needLogUrls.containsKey(url)) {
            String paramStr = getParams(request);
            log.info("url: {}  at {} query: {}", url, new Date(), paramStr);
            Log log = new Log();
            log.setUrl(url + paramStr);
            log.setOperateDate(new Date());
            logRepository.save(log);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    public static String getParams(HttpServletRequest request) {
        StringBuffer stringBuffer = new StringBuffer("");
        Enumeration<?> enumeration = request.getParameterNames();
        boolean firstParam = true;	//是否第一个参数
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            String value = request.getParameter(key);
            if (firstParam){
                stringBuffer.append("?");
            }else{
                stringBuffer.append("&");
            }

            stringBuffer.append(key).append("=").append(value);
            firstParam = false;
        }
        return stringBuffer.toString();

    }
}
