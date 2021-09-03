package io.jmix2mvp.petclinic.graphql.scalar;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;

import java.util.Currency;

public class CurrencyScalarType {

    public static final GraphQLScalarType type = new GraphQLScalarType("currency",
            "Currency Type", new Coercing() {
        @Override
        public Object serialize(Object dataFetcherResult) {
            return ((Currency) dataFetcherResult).getCurrencyCode();
        }

        @Override
        public Object parseValue(Object input) {
            return Currency.getInstance((String)input);
        }

        @Override
        public Object parseLiteral(Object input) {
            return parseValue(input);
        }
    });
}
