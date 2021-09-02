package io.jmix2mvp.petclinic.graphql.scalar;

import graphql.schema.GraphQLScalarType;
import io.jmix2mvp.petclinic.graphql.scalar.coercing.IntegerCoercing;


public class IntegerScalar {

    public static GraphQLScalarType type =
            (new GraphQLScalarType.Builder())
                    .name("Integer")
                    .description("Integer type")
                    .coercing(new IntegerCoercing()).build();

}
