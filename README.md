# feddi-demo-shop

Demo project accompanying an article about [feddi](https://feddi.dev), the first JVM-native GraphQL federation gateway. Three real, independently runnable GraphQL subgraphs (Products, Reviews, Users) built with Spring Boot and Spring GraphQL, federated through feddi.

## Requirements

- Java 25 for building and running the subgraphs (the Gradle build does not run on newer JDKs yet). The feddi Gateway itself runs on Java 25 or later.
- A free account on [feddi.dev](https://feddi.dev) and the feddi CLI, see [Get Started](https://feddi.dev/get-started)

## Subgraphs

| Subgraph | URL | Schema |
|---|---|---|
| `products` | http://localhost:4001 | `products/src/main/resources/graphql/schema.graphqls` |
| `reviews`  | http://localhost:4002 | `reviews/src/main/resources/graphql/schema.graphqls`, contributes fields to `Product` and `User` |
| `users`    | http://localhost:4003 | `users/src/main/resources/graphql/schema.graphqls` |

Each subgraph serves GraphiQL at `/graphiql` and a short info page at `/`.

## Running the subgraphs

Each subgraph is a standalone Spring Boot application. Start all three at once:

```bash
./gradlew startAll --parallel
```

`Ctrl+C` stops all three together. To run just one, start its `bootRun` task directly, e.g. `./gradlew :products:bootRun`.

## Quick start

The full walkthrough is in the article. In short, after installing and authenticating the feddi CLI, create a graph and a variant named `demo`, then register and publish the three subgraphs:

```bash
feddi subgraph create <variant-id> products
feddi subgraph publish <products-subgraph-id> --schema ./products/src/main/resources/graphql/schema.graphqls --config '{"url":"http://localhost:4001/graphql"}'

feddi subgraph create <variant-id> users
feddi subgraph publish <users-subgraph-id> --schema ./users/src/main/resources/graphql/schema.graphqls --config '{"url":"http://localhost:4003/graphql"}'

feddi subgraph create <variant-id> reviews
feddi subgraph publish <reviews-subgraph-id> --schema ./reviews/src/main/resources/graphql/schema.graphqls --config '{"url":"http://localhost:4002/graphql"}'
```

Each publish composes the supergraph again. Once all three are published, the gateway answers queries across all subgraphs, for example:

```graphql
{
  product(id: "1") {
    name
    reviews {
      body
      author {
        name
      }
    }
  }
}
```

## feddi Gateway config

`feddi-gateway.yml.sample` is the finished configuration used for this demo: GraphiQL enabled, connected to the feddi Platform with a short poll interval so schema changes show up quickly. The sample shipped with the gateway distribution only contains commented options. Copy this one to `feddi-gateway/feddi-gateway.yml` and fill in your own `graph-variant-key`:

```bash
cp feddi-gateway.yml.sample feddi-gateway/feddi-gateway.yml
```

The `feddi-gateway/` directory itself (the downloaded distribution) is gitignored, get it from [feddi.dev/downloads](https://feddi.dev/downloads).

## Examples

### Breaking-change check

`examples/breaking-change/users-schema.graphqls` is a modified copy of the Users schema in which `User.name` is split into `firstName` and `lastName`. It is not used by the Users service, it only exists to reproduce the breaking-change check from the article:

```bash
feddi subgraph check <users-subgraph-id> --schema examples/breaking-change/users-schema.graphqls
```

To reproduce the `(has usage)` output, first run the example query above through the gateway (for example in GraphiQL at http://localhost:8080/graphiql). The platform rolls usage up every five minutes, so wait about five minutes after the query. Until then the check reports `(no usage)`. See `examples/breaking-change/README.md`.

### In-process Java extension

`examples/java-extension` is a `SubgraphRequestHeaderCustomizer` for the feddi Gateway that replaces the caller's `Authorization` header with an internal service token. It runs inside the gateway process, no extra service and no HTTP callback. It builds against the latest released gateway extension. See `examples/java-extension/README.md` for build and setup.

## Support

Questions and feedback: [feddi Community Support](https://github.com/feddi-dev/feddi-community-support).
