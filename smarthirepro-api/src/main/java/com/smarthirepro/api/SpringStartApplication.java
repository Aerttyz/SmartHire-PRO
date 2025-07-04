package com.smarthirepro.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.smarthirepro.api",
        "com.smarthirepro.core",
        "com.smarthirepro.domain"
})
@EntityScan(basePackages = {
        "com.smarthirepro.api.models",
        "com.smarthirepro.domain.model"
})
@EnableJpaRepositories(basePackages = "com.smarthirepro.api.repositories")
public class SpringStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringStartApplication.class, args);
    }
}
