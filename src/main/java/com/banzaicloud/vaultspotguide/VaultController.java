package com.banzaicloud.vaultspotguide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.VaultMount;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class VaultController {

    @Autowired
    private VaultOperations vaultOperations;

    @GetMapping("/mounts")
    public Map<String, VaultMount> mounts() {
        return vaultOperations.opsForSys().getMounts();
    }

    @GetMapping("/secrets")
    public List<String> secrets() {
        return vaultOperations.list("secret/metadata/");
    }

    @PostMapping(value = "/secrets")
    public void createSecret(@RequestBody  Map<String, String> body) {
        vaultOperations.write("secret/data/", body);
    }

    @RequestMapping("/")
    public String health() {
        return "OK";
    }
}
