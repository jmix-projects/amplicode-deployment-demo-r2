package io.jmix2mvp.petclinic.graphql.scalar;

import graphql.schema.GraphQLScalarType;
import io.jmix2mvp.petclinic.graphql.scalar.coercing.DoubleCoercing;


public class DoubleScalar {

    public static GraphQLScalarType type =
            (new GraphQLScalarType.Builder())
                    .name("Double")
                    .description("Double type")
                    .coercing(new DoubleCoercing()).build();

}
