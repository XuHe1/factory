package com.kyx.factory.support.db;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 所有数据模型类的基类.
 *
 * Created by wangle on 5/10/16.
 */
@MappedSuperclass
@Getter
public abstract class BaseDomainWithSortOrder extends BaseDomain {
    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder = 0;

    public BaseDomainWithSortOrder setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }
}
