package com.kyx.factory.exception;

/**
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/
public final class ErrorCode {

    private static final String ERROR_CODE_PREFIX = "FA";

    public static final String UNKNOWN               = ERROR_CODE_PREFIX + "110001";
    public static final String PARAM_INVALID = ERROR_CODE_PREFIX + "200001";

    public static final String BAD_REQUEST = ERROR_CODE_PREFIX + "110002";
    public static final String SN_CONFLICT = ERROR_CODE_PREFIX + "200002";

    public static final String ALREADY_EXISTS = ERROR_CODE_PREFIX + "130001";
    public static final String NOT_EXISTS = ERROR_CODE_PREFIX + "130002";

    public static final String FACTORY_NOT_EXISTS = ERROR_CODE_PREFIX + "130003";
    public static final String PRODUCT_LINE_NOT_EXISTS = ERROR_CODE_PREFIX + "130004";
    public static final String DEVICE_NOT_SUPPORT = ERROR_CODE_PREFIX + "130005";
    public static final String SN_SUFFIX_ERROR = ERROR_CODE_PREFIX + "130006";
}