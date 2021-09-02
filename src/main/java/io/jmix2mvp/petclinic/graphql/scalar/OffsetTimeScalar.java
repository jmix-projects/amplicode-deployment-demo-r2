package io.jmix2mvp.petclinic.graphql.scalar;

import graphql.schema.GraphQLScalarType;
import io.jmix2mvp.petclinic.graphql.scalar.coercing.BaseDateCoercing;

import java.time.Instant;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

// @TODO Realize class
public class OffsetTimeScalar {

    public static final String OFFSET_DATE_TIME_FORMAT = "'HH:mm:ss+hh:mm'";

    public static GraphQLScalarType type =
            (new GraphQLScalarType.Builder())
                    .name("OffsetDateTime")
                    .description("Date type with offset")
                    .coercing(new BaseDateCoercing<OffsetTime, String>(OFFSET_DATE_TIME_FORMAT, OffsetTime.class){
                        @Override
                        public String doSerialize(OffsetTime input) {
                            return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(input);
                        }

                        protected OffsetTime parseString(String value) {
                            return OffsetTime.from(
                                    Instant.from(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(value))
                                            .atZone(ZoneId.systemDefault()));
                        }
                    }).build();
}
