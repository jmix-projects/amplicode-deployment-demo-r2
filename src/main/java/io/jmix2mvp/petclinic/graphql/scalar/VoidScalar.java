package io.jmix2mvp.petclinic.graphql.scalar;

import graphql.schema.GraphQLScalarType;
import io.jmix2mvp.petclinic.graphql.scalar.coercing.VoidCoercing;


public class VoidScalar {

    public static GraphQLScalarType type =
            (new GraphQLScalarType.Builder())
                    .name("Void")
                    .description("Void type")
                    .coercing(new VoidCoercing()).build();

}
