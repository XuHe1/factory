package com.kyx.factory.dal.repository;

import com.kyx.factory.dal.domain.ProductOrder;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author h.xu
 * @create 2017-06-05 下午1:40
 **/

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    //@Query("select p from ProductOrder p where p.username = ? for update")
    //ProductOrder findByUsername(String username);
    List<ProductOrder> getByUsername(String username);
    ProductOrder findTopByDevice(String device,  Sort sort);
}
