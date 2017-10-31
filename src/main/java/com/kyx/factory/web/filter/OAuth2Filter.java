package com.kyx.factory.web.filter;

import com.etianxia.openapi.cache.ICache;
import com.etianxia.openapi.cache.NonceOnSimpleMemCache;
import com.google.common.collect.Maps;
import com.kyx.factory.exception.ErrorCode;
import com.kyx.factory.exception.ErrorEnum;
import com.kyx.factory.support.json.JsonObject;
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
            log.debug("auth_parameter_absent");
            JsonObject jsonObject = new JsonObject();
            jsonObject.setCode(ErrorCode.PARAMETER_ABSENT);
            jsonObject.setMsg(ErrorEnum.getByCode(ErrorCode.PARAMETER_ABSENT).getMsg());
            jsonObject.setMeta(meta(request));
            result = JsonUtils.toJson(jsonObject);
            response.getWriter().write(result);

        } else if (!this.checkTimeStamp(paramMap)) {
            log.debug("timestamp_refused");
            JsonObject jsonObject = new JsonObject();
            jsonObject.setCode(ErrorCode.TIMESTAMP_REFUSED);
            jsonObject.setMsg(ErrorEnum.getByCode(ErrorCode.TIMESTAMP_REFUSED).getMsg());
            jsonObject.setMeta(meta(request));
            result = JsonUtils.toJson(jsonObject);
            response.getWriter().write(result);

        } else if(!this.checkNonce(paramMap)) {
            log.debug("nonce_used");
            JsonObject jsonObject = new JsonObject();
            jsonObject.setCode(ErrorCode.NONCE_USED);
            jsonObject.setMsg(ErrorEnum.getByCode(ErrorCode.NONCE_USED).getMsg());
            jsonObject.setMeta(meta(request));
            result = JsonUtils.toJson(jsonObject);
            response.getWriter().write(result);

        } else if(!this.checkSignature(request)) {
            log.debug("signature_invalid");
            JsonObject jsonObject = new JsonObject();
            jsonObject.setCode(ErrorCode.SIGNATURE_INVALID);
            jsonObject.setMsg(ErrorEnum.getByCode(ErrorCode.SIGNATURE_INVALID).getMsg());
            jsonObject.setMeta(meta(request));
            result = JsonUtils.toJson(jsonObject);
            response.getWriter().write(result);

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
