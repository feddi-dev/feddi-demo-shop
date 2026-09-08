package dev.feddi.demoshop.products;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class ProductController {

    private static final Map<String, Product> PRODUCTS = Map.of(
            "1", new Product("1", "Mechanical Keyboard"),
            "2", new Product("2", "Ultrawide Monitor")
    );

    @QueryMapping
    public Product product(@Argument String id) {
        return PRODUCTS.get(id);
    }
}
