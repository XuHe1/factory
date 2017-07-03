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
    public static final String SN_PREFIX_ERROR = ERROR_CODE_PREFIX + "130006";

    public static final String MISS_DEVICE = ERROR_CODE_PREFIX + "000001";
    public static final String SN_PATTER_ERROR = ERROR_CODE_PREFIX + "000002";
    public static final String MISS_FACTORY = ERROR_CODE_PREFIX + "000003";
    public static final String MISS_PRODUCT_LINE = ERROR_CODE_PREFIX + "000004";
    public static final String MISS_HW_VERSION = ERROR_CODE_PREFIX + "000005";
    public static final String MISS_SW_VERSION = ERROR_CODE_PREFIX + "000006";
    public static final String MISS_CHIP_ID = ERROR_CODE_PREFIX + "000007";
    public static final String SN_LENGTH = ERROR_CODE_PREFIX + "000008";
    public static final String MISS_ICCID = ERROR_CODE_PREFIX + "000009";
    public static final String MISS_GPS = ERROR_CODE_PREFIX + "000010";
    public static final String MISS_FLASH = ERROR_CODE_PREFIX + "000011";
    public static final String MISS_EEPROM = ERROR_CODE_PREFIX + "000012";
    public static final String MISS_GPRS = ERROR_CODE_PREFIX + "000013";
    public static final String MISS_BATTERY_VOLTAGE = ERROR_CODE_PREFIX + "000014";
    public static final String MISS_ELECTRIC_CURRENT = ERROR_CODE_PREFIX + "000015";
    public static final String MISS_ACCE_X = ERROR_CODE_PREFIX + "000016";
    public static final String MISS_ACCE_Y = ERROR_CODE_PREFIX + "000017";
    public static final String MISS_ACCE_Z = ERROR_CODE_PREFIX + "000018";
    public static final String MISS_GYRO_X = ERROR_CODE_PREFIX + "000019";
    public static final String MISS_GYRO_Y = ERROR_CODE_PREFIX + "000020";
    public static final String MISS_GYRO_Z = ERROR_CODE_PREFIX + "000021";
    public static final String MISS_TEST_RESULT = ERROR_CODE_PREFIX + "000022";
    public static final String MISS_SN = ERROR_CODE_PREFIX + "000023";

    public static final String MISS_ORDER_PREFIX = ERROR_CODE_PREFIX + "000024";
    public static final String MISS_ORDER_QUANTITY = ERROR_CODE_PREFIX + "000025";
    public static final String QUANTITY_MUST_NUM = ERROR_CODE_PREFIX + "000026";
    public static final String MISS_DELIVERY_COUNT = ERROR_CODE_PREFIX + "000027";
    public static final String DELIVERY_COUNT_MUST_NUM = ERROR_CODE_PREFIX + "000028";
    public static final String MISS_USER_NAME = ERROR_CODE_PREFIX + "000029";
    public static final String MISS_PASSWORD = ERROR_CODE_PREFIX + "000030";

    public static final String USER_ALREADY_EXISTS = ERROR_CODE_PREFIX + "000031";
    public static final String NO_START_SN = ERROR_CODE_PREFIX + "000032";
    public static final String OPERATION_INVALID = ERROR_CODE_PREFIX + "000033";


    public static final String USER_NAME_ERROR = ERROR_CODE_PREFIX + "000034";
    public static final String PASSWORD_ERROR = ERROR_CODE_PREFIX + "000035";
    public static final String ORDER_STOP = ERROR_CODE_PREFIX + "000036";
    public static final String NO_SN_AVAILABLE = ERROR_CODE_PREFIX + "000037";

    public static final String SN_ALREADY_INITIALIZED = ERROR_CODE_PREFIX + "000038";
    public static final String MISS_START_SN = ERROR_CODE_PREFIX + "000039";
    public static final String ORDER_PREFIX_ERROR = ERROR_CODE_PREFIX + "000040";
    public static final String FIRMWARE_GET_ERROR = ERROR_CODE_PREFIX + "000041";
    public static final String FIRMWARE_VERSION_GET_ERROR = ERROR_CODE_PREFIX + "000042";
    public static final String MISS_ORDER_ID = ERROR_CODE_PREFIX + "000043";
    public static final String MISS_LAST_CHECK_END = ERROR_CODE_PREFIX + "000044";
    public static final String MISS_DOWNLOAD_START = ERROR_CODE_PREFIX + "000045";
    public static final String MISS_CHECK_START = ERROR_CODE_PREFIX + "000046";
    public static final String MISS_CHECK_END = ERROR_CODE_PREFIX + "000047";
    public static final String NO_STATISTIC =  ERROR_CODE_PREFIX + "000048";
    public static final String NO_ORDER =  ERROR_CODE_PREFIX + "000049";
    public static final String QUANTITY_CAN_NOT_INCREASE = ERROR_CODE_PREFIX + "000050";
    public static final String TIMESTAMP_INVALID = ERROR_CODE_PREFIX + "000051";
    public static final String DELIVERY_COUNT_OVER = ERROR_CODE_PREFIX + "000052";
}