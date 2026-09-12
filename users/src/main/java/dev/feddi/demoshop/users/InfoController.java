package dev.feddi.demoshop.users;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    private static final String INFO = """
            feddi-demo-shop — users subgraph

            Part of the feddi demo project.
            https://github.com/feddi-dev/feddi-demo-shop

            GraphQL endpoint:  POST /graphql
            GraphiQL:          /graphiql

            Example query:
              { user(id: "1") { id name } }

            Sample data: users with id "1" and "2".
            """;

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String info() {
        return INFO;
    }
}
