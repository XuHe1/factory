package com.kyx.factory.support.db;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * 所有数据模型类的基类.
 *
 * Created by wangle on 5/10/16.
 */
@MappedSuperclass
@Getter
public abstract class BaseDomainWithTime implements Serializable {
    @CreatedDate
    @Column(name = "gmt_created", nullable = false)
    private Date gmtCreated;

    @LastModifiedDate
    @Column(name = "gmt_updated", nullable = false)
    private Date gmtUpdated;

    public BaseDomainWithTime setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
        return this;
    }

    public BaseDomainWithTime setGmtUpdated(Date gmtUpdated) {
        this.gmtUpdated = gmtUpdated;
        return this;
    }

    public BaseDomainWithTime updateTime() {
        Date now = new Date();
        return this.setGmtCreated(now).setGmtUpdated(now);
    }
}
