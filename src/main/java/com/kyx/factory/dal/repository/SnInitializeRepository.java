package com.kyx.factory.dal.repository;

import com.kyx.factory.dal.domain.SnInitialize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author h.xu
 * @create 2017-06-05 下午1:40
 **/

public interface SnInitializeRepository extends JpaRepository<SnInitialize, Long> {
    List<SnInitialize> getByDevice(String device);
}
