package com.banzaicloud.vaultspotguide;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.authentication.KubernetesAuthentication;
import org.springframework.vault.authentication.KubernetesAuthenticationOptions;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.config.AbstractVaultConfiguration;
import org.springframework.vault.config.EnvironmentVaultConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Configuration
@Import(value = EnvironmentVaultConfiguration.class)
public class VaultConfiguration extends AbstractVaultConfiguration {

    private String role;

    @Override
    public VaultEndpoint vaultEndpoint() {
        var endpoint = new VaultEndpoint();
        endpoint.setScheme("http");
        return endpoint;
    }

    @Override
    public ClientAuthentication clientAuthentication() {
        File vaultTokenFile = new File(System.getProperty("user.home") + "/.vault-token");
        if (vaultTokenFile.exists()) {
            try {
                return new TokenAuthentication(Files.readString(vaultTokenFile.toPath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            var options = KubernetesAuthenticationOptions.builder().role(role).build();
            return new KubernetesAuthentication(options, restOperations());
        }
    }
}
