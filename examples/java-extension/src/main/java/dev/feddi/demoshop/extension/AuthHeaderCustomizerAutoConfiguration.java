package dev.feddi.demoshop.extension;

import dev.feddi.federation.extension.ConfigurableExtension;
import dev.feddi.federation.extension.SubgraphRequestHeaderCustomizer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Replaces the Authorization header on every subgraph request with an
 * internal service-to-service token, instead of letting the caller's own
 * token through. DefaultSubgraphClient forwards the caller's Authorization
 * unchanged by default (trust-boundary translation: the gateway
 * authenticates the caller, subgraphs trust the gateway's own service
 * identity instead), so this customizer overrides it with headers.set(...)
 * rather than adding a new header.
 */
@AutoConfiguration
public class AuthHeaderCustomizerAutoConfiguration implements ConfigurableExtension<AuthConfig> {

    // ServiceTokenProvider isn't a published interface an application
    // would provide its own competing bean for, it's this example's own
    // stand-in class, so no @ConditionalOnMissingBean fallback here, it's
    // registered unconditionally. It has to live in a separate nested
    // configuration class rather than a @Bean method on this one:
    // AuthHeaderCustomizerAutoConfiguration needs ServiceTokenProvider
    // injected into its own constructor below, so a @Bean method here
    // would be a circular dependency.
    @Configuration
    static class ServiceTokenProviderConfiguration {
        @Bean
        ServiceTokenProvider serviceTokenProvider() {
            return new ServiceTokenProvider();
        }
    }

    private final ServiceTokenProvider tokenProvider;
    private boolean enabled = true;

    public AuthHeaderCustomizerAutoConfiguration(ServiceTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public String configNamespace() {
        return "auth";
    }

    @Override
    public Class<AuthConfig> configType() {
        return AuthConfig.class;
    }

    @Override
    public void onConfigLoaded(AuthConfig config) {
        this.enabled = config.isEnabled();
    }

    @Bean
    public SubgraphRequestHeaderCustomizer authHeaderCustomizer() {
        return (headers, subgraphName, context) -> {
            if (enabled) {
                headers.set("Authorization", "Bearer " + tokenProvider.currentToken());
            }
        };
    }
}
