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
    SN_SUFFIX_ERROR(ErrorCode.SN_SUFFIX_ERROR, "SN号前缀错误"),
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
    MISS_TEST_RESULT(ErrorCode.MISS_TEST_RESULT , "testResult不能为空");

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
