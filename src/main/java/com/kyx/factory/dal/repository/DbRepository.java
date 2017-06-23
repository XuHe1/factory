package com.kyx.factory.dal.repository;

import com.kyx.factory.dal.domain.DeviceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/

public interface DbRepository extends JpaRepository<DeviceData, Long> {


    @Query(value = "select * from schema_version", nativeQuery = true)
    List<Object[]> getSchemaVersion();


}
