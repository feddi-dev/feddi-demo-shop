package dev.feddi.demoshop.products;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    private static final String INFO = """
            feddi-demo-shop — products subgraph

            Part of the feddi demo project.
            https://github.com/feddi-dev/feddi-demo-shop

            GraphQL endpoint:  POST /graphql
            GraphiQL:          /graphiql

            Example query:
              { product(id: "1") { id name } }

            Sample data: products with id "1" and "2".
            """;

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String info() {
        return INFO;
    }
}
