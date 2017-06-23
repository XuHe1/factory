package com.kyx.factory.dal.repository;

import com.kyx.factory.dal.domain.DeviceData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/

public interface DeviceDataRepository extends JpaRepository<DeviceData, Long> {
    List<DeviceData> getBySn(String sn);
    Page<DeviceData> findAllByFactory(Pageable var1, String factory);

    @Query("select sum(check_total) from DeviceData d where d.order_id = ?1")
    Long findTotalCostTime(String orderId);

    @Query(value = "select gps_count,count(id) as device_count from device_data where order_id = ?1 group by gps_count", nativeQuery = true)
    List<Object[]> getGpsCount(String orderId);

    @Query(value = "select d.battery_voltage from DeviceData d where d.order_id = ?1")
    Integer[] getBatteryVoltage(String orderNo);

    @Query(value = "select acce_x,acce_y,acce_z from device_data  where order_id = ?1", nativeQuery = true)

    List<Object[]> getAccelerator(String orderNo);
    @Query(value = "select gyro_x,gyro_y,gyro_z from device_data  where order_id = ?1", nativeQuery = true)
    List<Object[]> getGyroscope(String orderNo);

    @Query(value = "select d.flash from DeviceData d where d.order_id = ?1")
    Integer[] getFlash(String orderNo);

    @Query(value = "select d.gprs from DeviceData d where d.order_id = ?1")
    Integer[] getGprs(String orderNo);

    @Query(value = "select d.eeprom from DeviceData d where d.order_id = ?1")
    Integer[] getEeprom(String orderNo);

    @Query(value = "select  date(receive_time), test_result, count(id) from device_data where order_id = ?1 group by DATE(receive_time), test_result", nativeQuery = true)
    List<Object[]> getDailyStat(String orderNo);


}
