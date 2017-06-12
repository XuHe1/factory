package com.kyx.factory.dal.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 签名信息
 *
 * @author h.xu
 * @create 2017-06-12 下午3:09
 **/
@Getter
@Setter
public class SignInfo implements Serializable {
    private String sha256;
    private String md5sum;
    private String sha1;
    private String crc16;
}
