package com.kyx.factory.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/
@Configuration
public class DbConfig {
    @Bean(destroyMethod = "close")
    @ConfigurationProperties(prefix = "dataSource")
    public DataSource dataSource(){
        return new HikariDataSource();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory);
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    @Bean
    public MyDbMigrate dbMigrate() {
        return new MyDbMigrate();
    }
}
