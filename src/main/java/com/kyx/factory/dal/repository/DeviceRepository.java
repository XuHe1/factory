package com.kyx.factory.dal.repository;

import com.kyx.factory.dal.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @author wangle<thisiswangle@gmail.com>
 */
public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findBySn(String sn);
}
