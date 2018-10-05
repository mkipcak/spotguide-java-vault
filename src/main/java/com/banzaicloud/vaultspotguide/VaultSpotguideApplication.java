package com.banzaicloud.vaultspotguide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.vault.core.VaultOperations;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class VaultSpotguideApplication {

    @Autowired
    private VaultOperations vaultOperations;

    @PostConstruct
    private void postConstruct() {
        vaultOperations.opsForSys().getMounts().forEach((k, v) -> System.out.println(k + " = " + v.getType() + ": " + v.getDescription()));
    }

    public static void main(String[] args) {
        SpringApplication.run(VaultSpotguideApplication.class, args);
    }
}
