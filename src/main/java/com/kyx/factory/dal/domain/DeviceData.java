package com.kyx.factory.dal.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private Integer id;

    @Column(name = "device")
    private String device;

    @Column(name = "factory")
    private String factory;

    @Column(name = "product_line")
    private Integer productLine;

    @Column(name = "hw_version")
    private String hwVersion;

    @Column(name = "sw_version")
    private String swVersion;

    @Column(name = "chip_id")
    private String chipId;

    @Column(name = "sn")
    private String sn;

    @Column(name = "iccid")
    private String iccid;

    @Column(name = "gps_count")
    private Integer gpsCount;

    @Column(name = "flash")
    private Integer flash;

    @Column(name = "eeprom")
    private Integer eeprom;

    @Column(name = "gprs")
    private Integer gprs;

    @Column(name = "battery_voltage")
    private Integer batteryVoltage;

    @Column(name = "electric_current")
    private Integer electricCurrent;

    @Column(name = "acce_x")
    private String acceX;

    @Column(name = "acce_y")
    private String acceY;

    @Column(name = "acce_z")
    private String acceZ;

    @Column(name = "gyro_x")
    private String gyroX;

    @Column(name = "gyro_y")
    private String gyroY;

    @Column(name = "gyro_z")
    private String gyroZ;

    @Column(name = "test_result")
    private Integer testResult;

    @Column(name = "receive_time")
    private Date receiveTime;

}
