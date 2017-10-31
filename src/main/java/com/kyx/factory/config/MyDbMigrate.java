package com.kyx.factory.config;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;

/**
 * flyway
 *
 * @author h.xu
 * @create 2017-08-04 下午6:41
 **/

public class MyDbMigrate implements FlywayMigrationStrategy {

    @Override
    public void migrate(Flyway flyway) {
        flyway.repair();
        flyway.migrate();
    }
}
