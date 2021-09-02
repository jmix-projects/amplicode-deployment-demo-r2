package io.jmix2mvp.petclinic.graphql.scalar;

import graphql.schema.GraphQLScalarType;
import io.jmix2mvp.petclinic.graphql.scalar.coercing.UUIDCoercing;


public class UUIDScalar {

    public static GraphQLScalarType type =
            (new GraphQLScalarType.Builder())
                    .name("UUID")
                    .description("UUID type")
                    .coercing(new UUIDCoercing()).build();

}
