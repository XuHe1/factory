package com.kyx.factory.dal.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 可用sn号段
 *
 * @author h.xu
 * @create 2017-06-07 上午9:55
 **/
@Entity
@Table(name = SnRange.TABLE_NAME)
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SnRange implements Serializable {
    public static final String TABLE_NAME = "sn_range";

    @Id
    @Column(name = "request_id")
    private String request_id;

    @Column(name = "start_sn", unique = true)
    private String start_sn;

    @Column(name = "end_sn", unique = true)
    private String end_sn;
}
