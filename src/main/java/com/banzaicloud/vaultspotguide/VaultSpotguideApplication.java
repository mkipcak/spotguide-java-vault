package com.banzaicloud.vaultspotguide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class VaultSpotguideApplication {

    @PostConstruct
    private void postConstruct() {
    }

    public static void main(String[] args) {
        SpringApplication.run(VaultSpotguideApplication.class, args);
    }
}
