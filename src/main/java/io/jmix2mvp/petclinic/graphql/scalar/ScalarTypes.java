package io.jmix2mvp.petclinic.graphql.scalar;

import graphql.schema.GraphQLScalarType;
import io.jmix2mvp.petclinic.graphql.scalar.coercing.LocalCoercing;

public class ScalarTypes {
    public static final GraphQLScalarType LOCALE_TYPE = GraphQLScalarType.newScalar()
            .name("Locale123")
            .description("Locale")
            .coercing(new LocalCoercing())
            .build();
}

