# spotguide-java-vault
A spotguide for a Spring application which talks with Vault (with MySQL storage backend).


```bash
## Build and Test locally with Docker for Mac Kubernetes
docker build -t banzaicloud/spotguide-java-vault:latest .
helm dep update .banzaicloud/charts/spotguide-java-vault
helm upgrade --install spotguide-java-vault .banzaicloud/charts/spotguide-java-vault

# Check the application
kubectl port-forward deployment/spotguide-java-vault 8080
```

```bash
# Delete the Helm release
helm delete --purge spotguide-java-vault
kubectl delete secrets bank-vaults
```