package dev.feddi.demoshop.reviews;

import graphql.Scalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

/**
 * Federation directives (@key, @lookup) reference the FieldSelectionSet scalar
 * in their argument types. It carries a field-selection string on the wire,
 * so it reuses String's coercing — Spring GraphQL just needs it registered.
 */
@Configuration
public class GraphQlConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        GraphQLScalarType fieldSelectionSet = GraphQLScalarType.newScalar()
                .name("FieldSelectionSet")
                .description("Field selection syntax for @key and @provides directives")
                .coercing(Scalars.GraphQLString.getCoercing())
                .build();

        return wiringBuilder -> wiringBuilder.scalar(fieldSelectionSet);
    }
}
