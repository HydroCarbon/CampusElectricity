package io.hydrocarbon.campus.electricity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author HydroCarbon
 * @since 2024-04-21
 */
@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableCaching
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "io.hydrocarbon.campus.electricity.repository")
@EntityScan(basePackages = "io.hydrocarbon.campus.electricity.entity")
public class CampusElectricityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusElectricityApplication.class, args);
    }

}
