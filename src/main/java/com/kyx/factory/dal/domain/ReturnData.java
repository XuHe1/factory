package com.kyx.factory.dal.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * api调用返回构造
 *
 * @author h.xu
 * @create 2017-06-12 下午3:44
 **/

@Getter
@Setter
public class ReturnData<T> implements Serializable {
    private List<T> data;
}
