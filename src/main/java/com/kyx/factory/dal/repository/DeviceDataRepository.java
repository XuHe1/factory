package com.kyx.factory.dal.repository;

import com.kyx.factory.dal.domain.DeviceData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @author xuhe
 */

public interface DeviceDataRepository extends JpaRepository<DeviceData, Long> {
    List<DeviceData> getBySn(String sn);
    Page<DeviceData> findAllByDevice(Pageable var1,String device);
}
