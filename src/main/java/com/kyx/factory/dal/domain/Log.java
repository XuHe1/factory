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
 * 操作日志
 *
 * @author h.xu
 * @create 2017-06-01 下午1:51
 **/
@Entity
@Table(name = Log.TABLE_NAME)
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Log implements Serializable {
    public static final String TABLE_NAME = "log";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "url", columnDefinition = "varchar(800)")
    private String url;

    @Column(name = "operate_date")
    private Date operateDate;
}
