package com.banzaicloud.vaultspotguide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.VaultMount;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class VaultController {

    @Autowired
    private VaultOperations vaultOperations;

    @RequestMapping("/mounts")
    public Map<String, VaultMount> mounts() {
        return vaultOperations.opsForSys().getMounts();
    }

    @RequestMapping("/secrets")
    public List<String> secrets() {
        return vaultOperations.list("secret/metadata/");
    }

    @RequestMapping("/")
    public String health() {
        return "OK";
    }
}
