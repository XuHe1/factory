package com.kyx.factory.dal.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 附件
 *
 * @author h.xu
 * @create 2017-06-02 下午3:04
 **/
@Entity
@Table(name = Attachment.TABLE_NAME)
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Attachment implements Serializable {
    public static final String TABLE_NAME = "attachment";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="file_name")
    private String fileName;

    @Column(name = "content", columnDefinition = "longblob")
    private byte[] content;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
