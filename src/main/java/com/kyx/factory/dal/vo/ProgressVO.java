package com.kyx.factory.dal.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 生产进度
 *
 * @author h.xu
 * @create 2017-06-14 上午11:16
 **/
@Getter
@Setter
public class ProgressVO implements Serializable {
    private Long finishedCount;
    private Long failedCount;
    private Long costTime;
}
