package io.jmix2mvp.petclinic.graphql.scalar;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.GraphQLScalarType;
import io.leangen.graphql.util.Scalars;

import java.util.Base64;
import java.util.Currency;
import java.util.stream.IntStream;

public class ScalarTypes {
    public static final GraphQLScalarType CURRENCY_TYPE = GraphQLScalarType.newScalar()
            .name("currency")
            .description("Currency Type")
            .coercing(new Coercing<Currency, String>() {
                @Override
                public String serialize(Object dataFetcherResult) {
                    return ((Currency) dataFetcherResult).getCurrencyCode();
                }

                @Override
                public Currency parseValue(Object input) {
                    return Currency.getInstance((String) input);
                }

                @Override
                public Currency parseLiteral(Object input) {
                    return parseValue(input);
                }
            }).build();

    public static final GraphQLScalarType BASE_64_STRING = GraphQLScalarType.newScalar()
            .name("Base64String_Byte")
            .description("Base64-encoded binary")
            .coercing(new Coercing<Byte[], String>() {
                @Override
                public String serialize(Object dataFetcherResult) {
                    if (dataFetcherResult instanceof Byte[]) {
                        Byte[] objBytes = (Byte[]) dataFetcherResult;
                        byte[] bytes = new byte[objBytes.length];
                        for (int i = 0; i < objBytes.length; i++) {
                            bytes[i] = objBytes[i];
                        }
                        return Base64.getEncoder().encodeToString(bytes);
                    } else if (dataFetcherResult instanceof String) {
                        return (String) dataFetcherResult;
                    } else {
                        throw Scalars.serializationException(dataFetcherResult, String.class, Byte[].class);
                    }
                }

                @Override
                public Byte[] parseValue(Object input) {
                    if (input instanceof String) {
                        try {
                            byte[] bytes = Base64.getDecoder().decode((String) input);
                            return IntStream.range(0, bytes.length)
                                    .mapToObj(i -> bytes[i])
                                    .toArray(Byte[]::new);
                        } catch (IllegalArgumentException e) {
                            throw new CoercingParseValueException("Input string \"" + input + "\" is not a valid Base64 value", e);
                        }
                    }
                    if (input instanceof Byte[]) {
                        return (Byte[]) input;
                    }
                    throw Scalars.valueParsingException(input, String.class, byte[].class);
                }

                @Override
                public Byte[] parseLiteral(Object input) {
                    StringValue string = Scalars.literalOrException(input, StringValue.class);
                    try {
                        byte[] bytes = Base64.getDecoder().decode(string.getValue());
                        return IntStream.range(0, bytes.length)
                                .mapToObj(i -> bytes[i])
                                .toArray(Byte[]::new);
                    } catch (IllegalArgumentException e) {
                        throw new CoercingParseLiteralException("Input string \"" + input + "\" is not a valid Base64 value", e);
                    }
                }
            }).build();

}