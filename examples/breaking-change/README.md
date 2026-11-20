# Breaking-change example

`users-schema.graphqls` is a modified copy of `users/src/main/resources/graphql/schema.graphqls`: `User.name` is split into `firstName` and `lastName`. It is not used by the actual Users service and is not picked up by Spring Boot at runtime, it only exists so the breaking-change check shown in the article can be reproduced:

```bash
feddi subgraph check <users-subgraph-id> --schema examples/breaking-change/users-schema.graphqls
```

`<users-subgraph-id>` is the id printed by `feddi subgraph create <variant-id> users` during setup, see the article.
