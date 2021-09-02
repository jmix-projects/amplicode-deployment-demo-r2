package io.jmix2mvp.petclinic.graphql.scalar;

import graphql.schema.GraphQLScalarType;
import io.jmix2mvp.petclinic.graphql.scalar.coercing.BigDecimalCoercing;


public class BigDecimalScalar {

    public static GraphQLScalarType type =
            (new GraphQLScalarType.Builder())
                    .name("BigDecimal")
                    .description("BigDecimal type")
                    .coercing(new BigDecimalCoercing()).build();

}
