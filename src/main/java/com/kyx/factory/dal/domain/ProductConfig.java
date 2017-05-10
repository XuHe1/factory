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
 * Created by XuHe on 17/5/8.
 */
@Entity
@Table(name = ProductConfig.TABLE_NAME)
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductConfig implements Serializable{
    public final static String TABLE_NAME = "product_config";
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

    @Column(name = "start_sn")
    private String startSn;

    @Column(name = "sn_count")
    private Integer snCount;

    @Column(name = "end_sn")
    private Integer endSn;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

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

    public String getStartSn() {
        return startSn;
    }

    public void setStartSn(String startSn) {
        this.startSn = startSn;
    }

    public Integer getSnCount() {
        return snCount;
    }

    public void setSnCount(Integer snCount) {
        this.snCount = snCount;
    }

    public Integer getEndSn() {
        return endSn;
    }

    public void setEndSn(Integer endSn) {
        this.endSn = endSn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
