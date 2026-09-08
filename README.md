# feddi-demo-shop

Beispielprojekt zum feddi-Artikel im Java Magazin. Drei echte, unabhängig lauffähige GraphQL-Subgraphs (Products, Reviews, Users) auf Spring Boot + Spring GraphQL, föderiert über [feddi](https://feddi.dev), das erste JVM-native GraphQL-Federation-Gateway.

## Subgraphs

| Subgraph | Port | Schema |
|---|---|---|
| `products` | 4001 | `products/src/main/resources/graphql/schema.graphqls` |
| `reviews`  | 4002 | `reviews/src/main/resources/graphql/schema.graphqls` — trägt Felder zu `Product` und `User` bei |
| `users`    | 4003 | `users/src/main/resources/graphql/schema.graphqls` |

Jeder Subgraph ist ein eigenständiger Spring-Boot-Service, lokal startbar ohne die anderen:

```bash
./gradlew :products:bootRun
./gradlew :reviews:bootRun
./gradlew :users:bootRun
```

Setup- und Composition-Schritte gegen das feddi Gateway: siehe Artikel.
