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
@Table(name = ProductConfig.TABLE_NAME)
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductConfig implements Serializable {
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

}
