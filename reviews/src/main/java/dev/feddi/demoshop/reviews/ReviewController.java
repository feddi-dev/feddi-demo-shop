package dev.feddi.demoshop.reviews;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class ReviewController {

    private static final Map<String, Review> REVIEWS = Map.of(
            "1", new Review("1", "Great mechanical feel", "1", "1"),
            "2", new Review("2", "Love the resolution", "2", "2"),
            "3", new Review("3", "Would buy again", "1", "2")
    );

    @QueryMapping
    public Review review(@Argument String id) {
        return REVIEWS.get(id);
    }

    @QueryMapping
    public Product productById(@Argument String id) {
        return new Product(id);
    }

    @QueryMapping
    public User userById(@Argument String id) {
        return new User(id);
    }

    @SchemaMapping(typeName = "Product", field = "reviews")
    public List<Review> reviewsForProduct(Product product) {
        return REVIEWS.values().stream()
                .filter(review -> review.productId().equals(product.id()))
                .toList();
    }

    @SchemaMapping(typeName = "User", field = "reviews")
    public List<Review> reviewsForUser(User user) {
        return REVIEWS.values().stream()
                .filter(review -> review.userId().equals(user.id()))
                .toList();
    }
}
