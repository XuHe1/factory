package com.kyx.factory.dal.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 项目版本
 *
 * @author h.xu
 * @create 2017-06-12 下午3:05
 **/
@Getter
@Setter
public class ProjectVersion implements Serializable {
    private String version;
    private String size;
    private String update_timestamp;
    private int is_urgent;
    private String safe_upgrade;
    private String title;
    private String path;
    private String desc;
    private SignInfo sign;

}
