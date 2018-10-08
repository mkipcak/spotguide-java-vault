package com.banzaicloud.vaultspotguide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.VaultMount;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                .map((key) -> Map.of("id", key, "data", readSecret(key)))
                .collect(Collectors.toList());
    }

    @GetMapping("/secrets/{id}")
    public Map<String, Object> secrets(@PathVariable("key") String key) {
        return readSecret(key);
    }

    private Map<String, Object> readSecret(String key) {
        return vaultOperations.read("secret/data/" + key).getData();
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
