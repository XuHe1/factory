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
 * Created by kaiitsugyou on 16/12/2.
 */
@Entity
@Table(name = DeviceData.TABLE_NAME)
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DeviceData implements Serializable{
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Integer getProductLine() {
        return productLine;
    }

    public void setProductLine(Integer productLine) {
        this.productLine = productLine;
    }

    public String getHwVersion() {
        return hwVersion;
    }

    public void setHwVersion(String hwVersion) {
        this.hwVersion = hwVersion;
    }

    public String getSwVersion() {
        return swVersion;
    }

    public void setSwVersion(String swVersion) {
        this.swVersion = swVersion;
    }

    public String getChipId() {
        return chipId;
    }

    public void setChipId(String chipId) {
        this.chipId = chipId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public Integer getGpsCount() {
        return gpsCount;
    }

    public void setGpsCount(Integer gpsCount) {
        this.gpsCount = gpsCount;
    }

    public Integer getFlash() {
        return flash;
    }

    public void setFlash(Integer flash) {
        this.flash = flash;
    }

    public Integer getEeprom() {
        return eeprom;
    }

    public void setEeprom(Integer eeprom) {
        this.eeprom = eeprom;
    }

    public Integer getGprs() {
        return gprs;
    }

    public void setGprs(Integer gprs) {
        this.gprs = gprs;
    }

    public Integer getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(Integer batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public Integer getElectricCurrent() {
        return electricCurrent;
    }

    public void setElectricCurrent(Integer electricCurrent) {
        this.electricCurrent = electricCurrent;
    }

    public String getAcceX() {
        return acceX;
    }

    public void setAcceX(String acceX) {
        this.acceX = acceX;
    }

    public String getAcceY() {
        return acceY;
    }

    public void setAcceY(String acceY) {
        this.acceY = acceY;
    }

    public String getAcceZ() {
        return acceZ;
    }

    public void setAcceZ(String acceZ) {
        this.acceZ = acceZ;
    }

    public String getGyroX() {
        return gyroX;
    }

    public void setGyroX(String gyroX) {
        this.gyroX = gyroX;
    }

    public String getGyroY() {
        return gyroY;
    }

    public void setGyroY(String gyroY) {
        this.gyroY = gyroY;
    }

    public String getGyroZ() {
        return gyroZ;
    }

    public void setGyroZ(String gyroZ) {
        this.gyroZ = gyroZ;
    }

    public Integer getTestResult() {
        return testResult;
    }

    public void setTestResult(Integer testResult) {
        this.testResult = testResult;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }
}
