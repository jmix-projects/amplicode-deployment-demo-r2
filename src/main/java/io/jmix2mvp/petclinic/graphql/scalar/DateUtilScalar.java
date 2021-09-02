package io.jmix2mvp.petclinic.graphql.scalar;

import com.sun.istack.NotNull;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import io.jmix2mvp.petclinic.graphql.scalar.coercing.BaseScalarCoercing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// @TODO Realize class
public class DateUtilScalar {

    public static GraphQLScalarType type =
            (new GraphQLScalarType.Builder())
                    .name("DateUtil")
                    .description("DateUtil type")
                    .coercing(new BaseScalarCoercing() {
                        @Override
                        public Object serialize(@NotNull Object input) throws CoercingSerializeException {
                            return (input instanceof Date) ? input.toString() : null;
                        }

                        @Override
                        public Object parseValue(@NotNull Object input) throws CoercingParseValueException {
                            try {
                                return (input instanceof String) ? new SimpleDateFormat().parse((String) input) : null;
                            } catch (ParseException e) {
                                throw new CoercingParseValueException();
                            }
                        }
                    }).build();


}
