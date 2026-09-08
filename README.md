# feddi-demo-shop

Beispielprojekt zum feddi-Artikel im Java Magazin. Drei GraphQL-Subgraphs (Products, Reviews, Users), föderiert über [feddi](https://feddi.dev), das erste JVM-native GraphQL-Federation-Gateway.

## Subgraphs

- `products/schema.graphqls`
- `users/schema.graphqls`
- `reviews/schema.graphqls` — trägt Felder zu `Product` und `User` bei

Setup und Composition-Schritte: siehe Artikel.
