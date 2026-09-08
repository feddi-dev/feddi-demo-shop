package dev.feddi.demoshop.users;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class UserController {

    private static final Map<String, User> USERS = Map.of(
            "1", new User("1", "Ada Lovelace"),
            "2", new User("2", "Grace Hopper")
    );

    @QueryMapping
    public User user(@Argument String id) {
        return USERS.get(id);
    }
}
