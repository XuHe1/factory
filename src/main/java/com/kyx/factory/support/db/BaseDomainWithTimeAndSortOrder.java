package com.kyx.factory.support.db;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 所有数据模型类的基类.
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/
@MappedSuperclass
@Getter
public abstract class BaseDomainWithTimeAndSortOrder extends BaseDomainWithTime {
    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder = 0;

    public BaseDomainWithTimeAndSortOrder setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }
}
