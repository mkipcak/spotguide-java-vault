package com.banzaicloud.vaultspotguide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class VaultApplication {

    @PostConstruct
    private void postConstruct() {
    }

    public static void main(String[] args) {
        SpringApplication.run(VaultApplication.class, args);
    }
}
