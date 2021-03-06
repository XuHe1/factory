package com.kyx.factory.dal.domain;

import com.kyx.factory.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/
@Entity
@Table(name = DeviceData.TABLE_NAME)
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DeviceData implements Serializable {
    public final static String TABLE_NAME = "device_data";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = ErrorCode.MISS_ORDER_ID)
    @Column(name = "order_id")
    private String order_id;

    @NotNull(message = ErrorCode.MISS_DEVICE)
    @Column(name = "device")
    private String device;

    @NotNull(message = ErrorCode.MISS_FACTORY)
    @Column(name = "factory")
    private String factory;

    @NotNull(message = ErrorCode.MISS_PRODUCT_LINE)
    @Column(name = "product_line")
    private String product_line;

    //@NotNull(message = ErrorCode.MISS_HW_VERSION)
    @Column(name = "hw_version")
    private String hw_version;

    //@NotNull(message = ErrorCode.MISS_SW_VERSION)
    @Column(name = "sw_version")
    private String sw_version;

    //@NotNull(message = ErrorCode.MISS_CHIP_ID)
    @Column(name = "chip_id")
    private String chip_id;

    @Size(min = 7, max = 7, message = ErrorCode.SN_LENGTH)
    @Pattern(regexp = "^[A-Z]{1}[0-9]{6}$", message = ErrorCode.SN_PATTER_ERROR)
    @Column(name = "sn", unique = true)
    private String sn;

    //@NotNull(message = ErrorCode.MISS_ICCID)
    @Column(name = "iccid")
    private String iccid;

    //@NotNull(message = ErrorCode.MISS_GPS)
    @Column(name = "gps_count")
    private Integer gps_count;

    //@NotNull(message = ErrorCode.MISS_FLASH)
    @Column(name = "flash")
    private Integer flash;

    //@NotNull(message = ErrorCode.MISS_EEPROM)
    @Column(name = "eeprom")
    private Integer eeprom;

    //@NotNull(message = ErrorCode.MISS_GPRS)
    @Column(name = "gprs")
    private Integer gprs;

    //@NotNull(message = ErrorCode.MISS_BATTERY_VOLTAGE)
    @Column(name = "battery_voltage")
    private Integer battery_voltage;

    //@NotNull(message = ErrorCode.MISS_ELECTRIC_CURRENT)
    @Column(name = "electric_current")
    private Integer electric_current;

    //@NotNull(message = ErrorCode.MISS_ACCE_X)
    @Column(name = "acce_x")
    private String acce_x;

    //@NotNull(message = ErrorCode.MISS_ACCE_Y)
    @Column(name = "acce_y")
    private String acce_y;

    //@NotNull(message = ErrorCode.MISS_ACCE_Z)
    @Column(name = "acce_z")
    private String acce_z;

    //@NotNull(message = ErrorCode.MISS_GYRO_X)
    @Column(name = "gyro_x")
    private String gyro_x;

    //@NotNull(message = ErrorCode.MISS_GYRO_Y)
    @Column(name = "gyro_y")
    private String gyro_y;

    //@NotNull(message = ErrorCode.MISS_GYRO_Z)
    @Column(name = "gyro_z")
    private String gyro_z;

    @NotNull(message = ErrorCode.MISS_TEST_RESULT)
    @Column(name = "test_result")
    private Integer test_result;

    //@NotNull(message = ErrorCode.MISS_LAST_CHECK_END)
    @Column(name = "last_check_end")
    private Long last_check_end;

    //@NotNull(message = ErrorCode.MISS_DOWNLOAD_START)
    @Column(name = "download_start")
    private Long download_start;

    //@NotNull(message = ErrorCode.MISS_CHECK_START)
    @Column(name = "check_start")
    private Long check_start;

    //@NotNull(message = ErrorCode.MISS_CHECK_END)
    @Column(name = "check_end")
    private Long check_end;

    @Column(name = "check_total")
    private Long check_total;

    @Column(name = "receive_time")
    private Date receive_time;

    @Column(name = "invalid", columnDefinition = "int default 0")
    private Integer invalid;

    @Column(name = "can")
    private Integer can;

    @Column(name = "kline")
    private Integer kline;

}
