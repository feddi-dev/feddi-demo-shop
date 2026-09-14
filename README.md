# feddi-demo-shop

Demo project accompanying an article about [feddi](https://feddi.dev), the first JVM-native GraphQL federation gateway. Three real, independently runnable GraphQL subgraphs (Products, Reviews, Users) built with Spring Boot and Spring GraphQL, federated through feddi.

## Subgraphs

| Subgraph | URL | Schema |
|---|---|---|
| `products` | http://localhost:4001 | `products/src/main/resources/graphql/schema.graphqls` |
| `reviews`  | http://localhost:4002 | `reviews/src/main/resources/graphql/schema.graphqls` — contributes fields to `Product` and `User` |
| `users`    | http://localhost:4003 | `users/src/main/resources/graphql/schema.graphqls` |

Each subgraph serves GraphiQL at `/graphiql` and a short info page at `/`.

## Running the subgraphs

Each subgraph is a standalone Spring Boot application. Start all three at once:

```bash
./gradlew startAll --parallel
```

`Ctrl+C` stops all three together. To run just one, start its `bootRun` task directly, e.g. `./gradlew :products:bootRun`.

Setup and composition steps against the feddi Gateway: see the article.

## feddi Gateway config

`feddi-gateway.yml.sample` shows the config used for this demo: GraphiQL enabled, connected to the feddi Platform with a short poll interval so schema changes show up quickly. Copy it to `feddi-gateway/feddi-gateway.yml` and fill in your own `graph-variant-key`:

```bash
cp feddi-gateway.yml.sample feddi-gateway/feddi-gateway.yml
```

The `feddi-gateway/` directory itself (the downloaded distribution) is gitignored — see the article for how to obtain it.
