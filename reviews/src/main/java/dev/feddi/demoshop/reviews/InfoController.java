package dev.feddi.demoshop.reviews;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    private static final String INFO = """
            feddi-demo-shop — reviews subgraph

            Part of the feddi demo project.
            https://github.com/feddi-dev/feddi-demo-shop

            GraphQL endpoint:  POST /graphql
            GraphiQL:          /graphiql

            Contributes fields to Product and User: productById/userById
            resolve the shared entity so the feddi Gateway can fetch the
            "reviews" field contributed here.

            Example queries:
              { review(id: "1") { id body } }
              { productById(id: "1") { id reviews { id body } } }
              { userById(id: "2") { id reviews { id body } } }

            Sample data: reviews with id "1"-"3", linked to product/user ids "1" and "2".
            """;

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String info() {
        return INFO;
    }
}
