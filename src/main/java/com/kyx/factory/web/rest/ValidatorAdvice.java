package com.kyx.factory.web.rest;

import com.google.common.collect.Maps;
import com.kyx.factory.exception.ErrorEnum;
import com.kyx.factory.support.json.Error;
import com.kyx.factory.support.json.JsonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * handler  params validate error
 *
 * @author h.xu
 * @create 2017-05-16 上午10:27
 **/
@Order(1)
@Slf4j
@ControllerAdvice
public class ValidatorAdvice {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public JsonResp<?> handleValidationError(BindException ex , HttpServletRequest request) {
        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();
        String errorCode = error.getDefaultMessage();
        return Error.newError(ErrorEnum.getByCode(errorCode), meta(request));
    }

    private Map<String, Object> meta(HttpServletRequest request) {
        Map<String, Object> data = Maps.newHashMap();
        data.put("timestamp", new Date());
        data.put("path", request.getRequestURI());

        return data;
    }
}


