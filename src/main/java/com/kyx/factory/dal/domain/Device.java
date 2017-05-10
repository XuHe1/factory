package com.kyx.factory.dal.domain;

import com.kyx.factory.support.db.BaseDomainWithTime;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = Device.TABLE_NAME)
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Device extends BaseDomainWithTime {
    public final static String TABLE_NAME = "device";
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 48, nullable = false)
    private String udid;

    @Column(length = 20, nullable = false)
    private String sn;

    @Column(name = "secret_key", length = 48, nullable = false)
    private String secretKey;

    @Column(name = "is_enabled", columnDefinition="tinyint(1) default 1", nullable = false)
    private Boolean isEnabled;
}