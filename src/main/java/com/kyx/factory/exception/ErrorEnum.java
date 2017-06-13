package com.kyx.factory.exception;


public enum ErrorEnum {
    UNKNOWN(ErrorCode.UNKNOWN, "未知错误"),
    PARAM_INVALID(ErrorCode.PARAM_INVALID, "参数不合法"),
    ALREADY_EXISTS(ErrorCode.ALREADY_EXISTS, "sn号已存在"),
    NOT_EXISTS(ErrorCode.NOT_EXISTS, "资源不存在"),
    BAD_REQUEST(ErrorCode.BAD_REQUEST, "非法请求"),
    SN_CONFLICT(ErrorCode.SN_CONFLICT, "SN冲突"),
    FACTORY_NOT_EXISTS(ErrorCode.FACTORY_NOT_EXISTS, "工厂不存在" ),
    PRODUCT_LINE_NOT_EXISTS(ErrorCode.PRODUCT_LINE_NOT_EXISTS, "生产线不存在"),
    DEVICE_NOT_SUPPORT(ErrorCode.DEVICE_NOT_SUPPORT, "暂不支持该种设备"),
    SN_PREFIX_ERROR(ErrorCode.SN_PREFIX_ERROR, "SN号前缀错误"),
    MISS_DEVICE(ErrorCode.MISS_DEVICE, "device不能为空"),
    SN_PATTER_ERROR(ErrorCode.SN_PATTER_ERROR, "SN号格式错误"),
    MISS_FACTORY(ErrorCode.MISS_FACTORY , "factory不能为空"),
    MISS_PRODUCT_LINE(ErrorCode.MISS_PRODUCT_LINE , "productLine不能为空"),
    MISS_HW_VERSION(ErrorCode.MISS_HW_VERSION , "hwVersion不能为空"),
    MISS_SW_VERSION(ErrorCode.MISS_SW_VERSION , "swVersion不能为空"),
    MISS_CHIP_ID(ErrorCode.MISS_CHIP_ID , "chipId不能为空"),
    SN_LENGTH(ErrorCode.SN_LENGTH , "SN长度必须为7位"),
    MISS_ICCID(ErrorCode.MISS_ICCID , "iccid不能为空"),
    MISS_GPS(ErrorCode.MISS_GPS , "gps不能为空"),
    MISS_FLASH(ErrorCode.MISS_FLASH , "flash不能为空"),
    MISS_EEPROM(ErrorCode.MISS_EEPROM , "eeprom不能为空"),
    MISS_GPRS(ErrorCode.MISS_GPRS , "gprs不能为空"),
    MISS_BATTERY_VOLTAGE(ErrorCode.MISS_BATTERY_VOLTAGE , "batteryVoltage不能为空"),
    MISS_ELECTRIC_CURRENT(ErrorCode.MISS_ELECTRIC_CURRENT , "electricCurrent不能为空"),
    MISS_ACCE_X(ErrorCode.MISS_ACCE_X , "acceX不能为空"),
    MISS_ACCE_Y(ErrorCode.MISS_ACCE_Y , "acceY不能为空"),
    MISS_ACCE_Z(ErrorCode.MISS_ACCE_Z , "acceZ不能为空"),
    MISS_GYRO_X(ErrorCode.MISS_GYRO_X , "gyroX不能为空"),
    MISS_GYRO_Y(ErrorCode.MISS_GYRO_Y , "gyroY不能为空"),
    MISS_GYRO_Z(ErrorCode.MISS_GYRO_Z , "gyroZ不能为空"),
    MISS_TEST_RESULT(ErrorCode.MISS_TEST_RESULT , "testResult不能为空"),
    MISS_SN(ErrorCode.MISS_SN, "SN号不能为空"),
    MISS_ORDER_PREFIX(ErrorCode.MISS_ORDER_PREFIX, "订单号前缀不能为空"),
    MISS_ORDER_QUANTITY(ErrorCode.MISS_ORDER_QUANTITY, "设备总数不能为空"),
    QUANTITY_MUST_NUM(ErrorCode.QUANTITY_MUST_NUM, "设备总数必须为数字"),
    MISS_DELIVERY_COUNT(ErrorCode.MISS_DELIVERY_COUNT, "每次下发sn号数不能为空"),
    DELIVERY_COUNT_MUST_NUM(ErrorCode.DELIVERY_COUNT_MUST_NUM, "每次下发sn号数必须为数字"),
    MISS_USER_NAME(ErrorCode.MISS_USER_NAME, "账号不能为空"),
    MISS_PASSWORD(ErrorCode.MISS_PASSWORD, "密码不能为空"),
    USER_NAME_ERROR(ErrorCode.USER_NAME_ERROR, "账号错误"),
    PASSWORD_ERROR(ErrorCode.PASSWORD_ERROR, "密码错误"),
    USER_ALREADY_EXISTS(ErrorCode.USER_ALREADY_EXISTS, "用户名已存在"),
    NO_START_SN(ErrorCode.NO_START_SN, "未指定起始SN"),
    NO_SN_AVAILABLE(ErrorCode.NO_SN_AVAILABLE, "无可用SN号，请新建订单"),
    ORDER_STOP(ErrorCode.ORDER_STOP, "订单已暂停"),
    OPERATION_INVALID(ErrorCode.OPERATION_INVALID, "非法操作"),
    SN_ALREADY_INITIALIZED(ErrorCode.SN_ALREADY_INITIALIZED, "该类设备sn已初始化"),
    MISS_START_SN(ErrorCode.MISS_START_SN, "起始sn不能为空"),
    ORDER_PREFIX_ERROR(ErrorCode.ORDER_PREFIX_ERROR, "订单前缀必须由大小写字母组成"),
    FIRMWARE_GET_ERROR(ErrorCode.FIRMWARE_GET_ERROR, "获取固件项目异常"),
    FIRMWARE_VERSION_GET_ERROR(ErrorCode.FIRMWARE_VERSION_GET_ERROR, "获取固件版本所有版本异常"),
    MISS_ORDER_ID(ErrorCode.MISS_ORDER_ID, "order_id不能为空"),
    MISS_LAST_CHECK_END(ErrorCode.MISS_LAST_CHECK_END, "last_check_end不能为空"),
    MISS_DOWNLOAD_START(ErrorCode.MISS_DOWNLOAD_START, "download_start不能为空"),
    MISS_CHECK_START(ErrorCode.MISS_CHECK_START, "check_start不能为空"),
    MISS_CHECK_END(ErrorCode.MISS_CHECK_END, "check_end不能为空");


    private String code;
    private String msg;
    ErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

    public static ErrorEnum getByCode(String code) {
        for (ErrorEnum failure : ErrorEnum.values()) {
            if (failure.getCode().equals(code))
                return failure;
        }

        return UNKNOWN;
    }

    @Override
    public String toString() {
        return "<ErrorEnum>{" +
            "code='" + code + '\'' +
            ", msg='" + msg + '\'' +
            '}';
    }
}
