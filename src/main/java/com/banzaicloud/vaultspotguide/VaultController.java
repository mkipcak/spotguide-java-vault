package com.banzaicloud.vaultspotguide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.VaultMount;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class VaultController {

    @Autowired
    private VaultOperations vaultOperations;

    @GetMapping("/mounts")
    public Map<String, VaultMount> mounts() {
        return vaultOperations.opsForSys().getMounts();
    }

    @GetMapping("/secrets")
    public List<Map<String, Object>> secrets() {
        return vaultOperations.list("secret/metadata/")
                .stream()
                .map((key) -> vaultOperations.read("secret/data/" + key).getData())
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/secrets")
    public void createSecret(@RequestBody Map<String, String> body) {
        vaultOperations.write(
                "secret/data/" + UUID.randomUUID().toString(),
                Map.of("data", body)
        );
    }

    @RequestMapping("/")
    public String health() {
        return "OK";
    }
}
