# Java extension example

A `SubgraphRequestHeaderCustomizer` extension for the feddi Gateway: replaces the `Authorization` header on every subgraph request with an internal service-to-service token, derived from `ServiceTokenProvider` (a stand-in for whatever internal token logic an organization already has), instead of letting the caller's own token through. `DefaultSubgraphClient` forwards the caller's `Authorization` unchanged by default, this customizer overrides it. Runs alongside the gateway's default `SubgraphClient`, no need to reimplement HTTP transport, header forwarding, or GraphQL error mapping. See the article for context.

Depends on `dev.feddi:feddi-gateway-extension` from Maven Central (`latest.release`), no local publish step needed. Build this module and drop the jar into the gateway distribution's `libs/`:

```bash
cd examples/java-extension
../../gradlew build
cp build/libs/java-extension-example-1.0-SNAPSHOT.jar ../../feddi-gateway/libs/
```

Restart the gateway. Enabled by default, disable it (e.g. for local development, where the real internal token source usually isn't reachable) via `feddi-gateway.yml`:

```yaml
extensions:
  auth:
    enabled: false
```

Verified end-to-end: gateway restarted with this jar in `libs/`, all three demo subgraphs running, the same federated query from the article's Schema-Komposition section resolved correctly through it, including with a client-supplied `Authorization` header that got overridden as expected.
