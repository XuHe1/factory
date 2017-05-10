package com.kyx.factory.web.rest;

import com.kyx.factory.exception.ErrorEnum;
import com.kyx.factory.exception.GeneralException;
import com.kyx.factory.support.json.JsonResp;
import com.google.common.collect.Maps;
import com.kyx.factory.support.json.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/
@Slf4j
@ControllerAdvice("com.netstatng.device.rest")
public class ResourceAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    JsonResp<?> handleApiControllerException(Exception ex, HttpServletRequest request) {
        if (ex instanceof GeneralException) {
           // log.debug("GeneralException : {}", ((GeneralException) ex).getErrorEnum());
            return Error.newError(((GeneralException) ex).getErrorEnum(), meta(request));
        } else {
            //log.debug("Unknown exception details", ex);
            return Error.newError(ErrorEnum.UNKNOWN, meta(request));
        }
    }

    private Map<String, Object> meta(Exception ex, HttpServletRequest request) {
        Map<String, Object> data = Maps.newHashMap();
        data.put("timestamp", new Date());
        data.put("exception", ex.getClass().getCanonicalName());
        data.put("path", request.getRequestURI());
        data.put("cause", ex);

        return data;
    }

    private Map<String, Object> meta(HttpServletRequest request) {
        Map<String, Object> data = Maps.newHashMap();
        data.put("timestamp", new Date());
        data.put("path", request.getRequestURI());

        return data;
    }
}
