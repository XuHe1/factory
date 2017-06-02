package com.kyx.factory.dal.repository;

import com.kyx.factory.dal.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/

public interface LogRepository extends JpaRepository<Log, Long> {

}
