package com.kyx.factory.dal.domain;

import com.kyx.factory.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 设备sn初始化
 *
 * @author h.xu
 * @create 2017-06-05 下午7:11
 **/

@Entity
@Table(name = SnInitialize.TABLE_NAME)
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SnInitialize implements Serializable {
    public static final String TABLE_NAME = "sn_initialize";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = ErrorCode.MISS_DEVICE)
    @Column(name = "device")
    private String device;

    @NotNull(message = ErrorCode.MISS_START_SN)
    @Column(name = "start_sn")
    private Integer startSn;

    @Column(name = "create_time")
    private Date createTime;
}
