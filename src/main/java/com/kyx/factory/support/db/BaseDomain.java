package com.kyx.factory.support.db;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 所有数据模型类的基类.
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/
@MappedSuperclass
public abstract class BaseDomain implements Serializable {
}
