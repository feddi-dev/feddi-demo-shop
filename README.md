# feddi-demo-shop

Demo project accompanying an article about [feddi](https://feddi.dev), the first JVM-native GraphQL federation gateway. Three real, independently runnable GraphQL subgraphs (Products, Reviews, Users) built with Spring Boot and Spring GraphQL, federated through feddi.

## Subgraphs

| Subgraph | Port | Schema |
|---|---|---|
| `products` | 4001 | `products/src/main/resources/graphql/schema.graphqls` |
| `reviews`  | 4002 | `reviews/src/main/resources/graphql/schema.graphqls` — contributes fields to `Product` and `User` |
| `users`    | 4003 | `users/src/main/resources/graphql/schema.graphqls` |

Each subgraph serves GraphiQL at `/graphiql` and a short info page at `/`.

## Running the subgraphs

Each subgraph is a standalone Spring Boot application — start them in three separate terminals:

```bash
./gradlew :products:bootRun
```

```bash
./gradlew :reviews:bootRun
```

```bash
./gradlew :users:bootRun
```

Or start all three at once in the background from a single terminal:

```bash
./gradlew :products:bootRun &
./gradlew :reviews:bootRun &
./gradlew :users:bootRun &
```

Stop them again with `kill %1 %2 %3`, or `jobs -l` to find their PIDs.

Setup and composition steps against the feddi Gateway: see the article.
