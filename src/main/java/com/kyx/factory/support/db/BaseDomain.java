package com.kyx.factory.support.db;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 所有数据模型类的基类.
 *
 * Created by wangle on 5/10/16.
 */
@MappedSuperclass
public abstract class BaseDomain implements Serializable {
}
