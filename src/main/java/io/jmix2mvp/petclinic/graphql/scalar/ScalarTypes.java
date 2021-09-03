package io.jmix2mvp.petclinic.graphql.scalar;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;

import java.util.Currency;

public class ScalarTypes {
    public static final GraphQLScalarType CURRENCY_TYPE = GraphQLScalarType.newScalar()
            .name("currency")
            .description("Currency Type")
            .coercing(new Coercing() {
                @Override
                public Object serialize(Object dataFetcherResult) {
                    return ((Currency) dataFetcherResult).getCurrencyCode();
                }

                @Override
                public Object parseValue(Object input) {
                    return Currency.getInstance((String) input);
                }

                @Override
                public Object parseLiteral(Object input) {
                    return parseValue(input);
                }
            }).build();
}