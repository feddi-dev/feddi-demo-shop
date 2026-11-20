package dev.feddi.demoshop.extension;

/**
 * Stand-in for whatever an organization already uses internally to obtain
 * service-to-service tokens: a JWT signer, a cached client-credentials
 * token, a Vault lease, anything. The point of this example is that feddi
 * never needs to know which, it's a normal Java dependency.
 */
public class ServiceTokenProvider {

    public String currentToken() {
        return "demo-service-token";
    }
}
