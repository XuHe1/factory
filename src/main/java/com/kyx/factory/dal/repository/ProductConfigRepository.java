package com.kyx.factory.dal.repository;

import com.kyx.factory.dal.domain.ProductConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @author xuhe
 */

public interface ProductConfigRepository extends JpaRepository<ProductConfig, Long> {
    List<ProductConfig> getByDevice(String device);
}
