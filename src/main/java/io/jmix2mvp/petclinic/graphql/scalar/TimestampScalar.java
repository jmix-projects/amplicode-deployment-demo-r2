package io.jmix2mvp.petclinic.graphql.scalar;

import com.sun.istack.NotNull;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import io.jmix2mvp.petclinic.graphql.scalar.coercing.BaseScalarCoercing;

import java.sql.Timestamp;

// @TODO Realize class
public class TimestampScalar {

    public static GraphQLScalarType type =
            (new GraphQLScalarType.Builder())
                    .name("Timestamp")
                    .description("Timestamp SQL type")
                    .coercing(new BaseScalarCoercing() {
                        @Override
                        public Object serialize(@NotNull Object input) throws CoercingSerializeException {
                            return (input instanceof Timestamp) ? input.toString() : null;
                        }

                        @Override
                        public Object parseValue(@NotNull Object input) throws CoercingParseValueException {
                            return (input instanceof String) ? Timestamp.valueOf((String) input) : null;
                        }
                    }).build();


}
