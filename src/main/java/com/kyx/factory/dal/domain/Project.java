package com.kyx.factory.dal.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 项目
 *
 * @author h.xu
 * @create 2017-06-12 下午2:42
 **/

@Getter
@Setter
public class Project implements Serializable {
    private String id;
    private String name;
}
