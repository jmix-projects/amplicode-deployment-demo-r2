package io.jmix2mvp.petclinic.graphql.scalar;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import io.jmix2mvp.petclinic.graphql.scalar.coercing.LocalCoercing;

public class LocalType {

    public static GraphQLScalarType getLocal() {
        return new GraphQLScalarType("Locale123", "Locale", new LocalCoercing());
    }
}
