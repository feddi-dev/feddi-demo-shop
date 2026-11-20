package dev.feddi.demoshop.extension;

/**
 * Config POJO for the "auth" extension namespace, deserialized from
 * feddi-gateway.yml by ConfigurableExtension.onConfigLoaded.
 */
public class AuthConfig {

    private boolean enabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
