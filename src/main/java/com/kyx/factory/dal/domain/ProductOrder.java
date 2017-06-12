package com.kyx.factory.dal.domain;

import com.kyx.factory.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 生产订单
 *
 * @author h.xu
 * @create 2017-06-01 下午2:11
 **/
@Entity
@Table(name = ProductOrder.TABLE_NAME)
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder implements Serializable {
    public static final String TABLE_NAME = "product_order";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = ErrorCode.MISS_ORDER_PREFIX)
    @Pattern(regexp = "[a-zA-Z]*", message = ErrorCode.ORDER_PREFIX_ERROR)
    @Column(name = "order_no_prefix")
    private String orderNoPrefix;

    private String orderNo;

    @NotBlank(message = ErrorCode.MISS_DEVICE)
    @Column(name = "device")
    private String device;

    @NotNull(message = ErrorCode.MISS_ORDER_QUANTITY)
    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "start_sn")
    private Integer startSn;

    @Column(name = "sn_index")
    private Integer snIndex;

    @Column(name = "end_sn")
    private Integer endSn;

    @Column(name = "project")
    private String project;

    @NotBlank(message = ErrorCode.MISS_HW_VERSION)
    @Column(name = "fw_version")
    private String fwVersion;

    @Column(name = "fw_download")
    private String fwDownload;

    @NotBlank(message = ErrorCode.MISS_HW_VERSION)
    @Column(name = "hw_version")
    private String hwVersion;

    @NotBlank(message = ErrorCode.MISS_FACTORY)
    @Column(name = "factory")
    private String factory;

    @NotBlank(message = ErrorCode.MISS_USER_NAME)
    @Column(name = "username")
    private String username;

    @NotBlank(message = ErrorCode.MISS_PASSWORD)
    @Column(name = "password")
    private String password;

    @NotNull(message = ErrorCode.MISS_DELIVERY_COUNT)
    @Column(name = "delivery_count")
    private Integer deliveryCount;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "schedule_begin")
    private Date scheduleBegin;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "schedule_finish")
    private Date scheduleFinish;

    @Column(name = "attachment_id")
    private Long attachmentId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "state")
    private Integer state;

}
