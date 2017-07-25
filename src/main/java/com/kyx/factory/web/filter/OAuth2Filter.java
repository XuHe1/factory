package com.kyx.factory.web.filter;

import com.etianxia.openapi.cache.ICache;
import com.etianxia.openapi.cache.NonceOnSimpleMemCache;
import com.etianxia.openapi.utils.OAuth;
import com.google.common.collect.Maps;
import com.kyx.factory.exception.ErrorEnum;
import com.kyx.factory.support.json.Error;
import com.kyx.factory.util.EncryptUtil;
import com.kyx.factory.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * operations log
 *
 * @author h.xu
 * @create 2017-05-31 下午4:04
 **/
@Slf4j
@WebFilter(urlPatterns = {"/sn_range/user", "/order/user", "/product"})
public class OAuth2Filter implements Filter {

    private static final int DEFAULT_TIME_TOLERANCE = 120;

    private static final List<String> authParams = new ArrayList<>();

    private static ICache cache = new NonceOnSimpleMemCache();

    static {
        authParams.add("timestamp");
        authParams.add("nonce");
        authParams.add("signature");

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Map<String, String[]> paramMap = request.getParameterMap();

        String result;
        if (this.missOAuthParameters(paramMap)) {
            response.setStatus(((Integer) OAuth.Problems.TO_HTTP_CODE.get("parameter_absent")).intValue());
            result = JsonUtils.toJson( Error.newError(ErrorEnum.PARAMETER_ABSENT, meta(request)));
            response.getWriter().write(result);
            log.debug("auth_parameter_absent");
        } else if (!this.checkTimeStamp(paramMap)) {
            response.setStatus(((Integer) OAuth.Problems.TO_HTTP_CODE.get("timestamp_refused")).intValue());
            result = JsonUtils.toJson( Error.newError(ErrorEnum.TIMESTAMP_REFUSED, meta(request)));
            response.getWriter().write(result);
            log.debug("timestamp_refused");
        } else if(!this.checkNonce(paramMap)) {
            response.setStatus(((Integer) OAuth.Problems.TO_HTTP_CODE.get("nonce_used")).intValue());
            result = JsonUtils.toJson( Error.newError(ErrorEnum.NONCE_USED, meta(request)));
            response.getWriter().write(result);
            log.debug("nonce_used");
        } else if(!this.checkSignature(request)) {
            response.setStatus(((Integer) OAuth.Problems.TO_HTTP_CODE.get("signature_invalid")).intValue());
            result = JsonUtils.toJson( Error.newError(ErrorEnum.SIGNATURE_INVALID, meta(request)));
            response.getWriter().write(result);
            log.debug("signature_invalid");
        } else {
            filterChain.doFilter(request, response);
        }


    }

    @Override
    public void destroy() {

    }

    private Map<String, Object> meta(HttpServletRequest request) {
        Map<String, Object> data = Maps.newHashMap();
        data.put("timestamp", new Date());
        data.put("path", request.getRequestURI());

        return data;
    }

    private boolean missOAuthParameters(Map<String, String[]> parameterMap) {
        boolean isMissedField = false;
        Iterator i$ = authParams.iterator();

        while(i$.hasNext()) {
            String oAuthKey = (String)i$.next();
            if(!parameterMap.containsKey(oAuthKey)) {
                isMissedField = true;
                break;
            }
        }

        return isMissedField;
    }

    private boolean checkTimeStamp(Map<String, String[]> parameterMap) {
        try {
            String[] e = (String[])parameterMap.get("timestamp");
            if(e != null && e.length == 1) {
                int timestamp = Integer.parseInt(e[0]);
                if(Math.abs(getUnixStamp().intValue() - timestamp) < 120) {
                    return true;
                }
            }
        } catch (NumberFormatException var4) {
            log.error(var4.getMessage());
        }

        return false;
    }


    private boolean checkNonce(Map<String, String[]> parameterMap) {
        String[] nonces = (String[])parameterMap.get("nonce");
        return nonces != null && nonces.length == 1?!cache.isExist(nonces[0]):false;
    }

    private boolean checkSignature(HttpServletRequest request) {
        String sigature = this.getSignature(request);
        return sigature != null && sigature.equals(request.getParameter("signature"));
    }

    private Integer getUnixStamp() {
        return Integer.valueOf((int)(System.currentTimeMillis() / 1000L));
    }

    public static String getSignature(HttpServletRequest request) {

        String baseURL1 = request.getRequestURL().toString();

        StringBuffer stringBuffer = new StringBuffer(baseURL1);

        Collections.sort(authParams);

        boolean firstParam = true;
        for (String paramName : authParams) {
            if (!paramName.equals("signature")) {
                if (firstParam){
                    stringBuffer.append("?");
                }else{
                    stringBuffer.append("&");
                }
                String value = request.getParameter(paramName);
                stringBuffer.append(paramName).append("=").append(value);
                firstParam = false;
            }


        }
        return EncryptUtil.hmacSHA1Encrypt(stringBuffer.toString(), EncryptUtil.SECRET_KEY);

    }
}
