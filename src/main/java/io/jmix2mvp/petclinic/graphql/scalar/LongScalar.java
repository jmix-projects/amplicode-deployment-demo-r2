package io.jmix2mvp.petclinic.graphql.scalar;

import graphql.schema.GraphQLScalarType;
import io.jmix2mvp.petclinic.graphql.scalar.coercing.LongCoercing;


public class LongScalar {

    public static GraphQLScalarType type =
            (new GraphQLScalarType.Builder())
                    .name("Long")
                    .description("Long type")
                    .coercing(new LongCoercing()).build();

}
