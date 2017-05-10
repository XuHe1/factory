package com.kyx.factory.exception;

/**
 * Created by wangle on 6/3/16.
 */
public final class AppFailureCode {
    private static final String ERROR_CODE_PREFIX = "FA";

    public static final String UNKNOWN               = ERROR_CODE_PREFIX + "110001";
    public static final String PARAM_INVALID = ERROR_CODE_PREFIX + "200001";
    public static final String ALREADY_EXISTS = ERROR_CODE_PREFIX + "130001";
    public static final String NOT_EXISTS = ERROR_CODE_PREFIX + "130002";
    public static final String BAD_REQUEST = ERROR_CODE_PREFIX + "110002";
    public static final String SN_CONFLICT = ERROR_CODE_PREFIX + "200002";

}
