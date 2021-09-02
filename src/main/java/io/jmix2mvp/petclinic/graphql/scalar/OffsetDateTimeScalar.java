package io.jmix2mvp.petclinic.graphql.scalar;

import graphql.schema.GraphQLScalarType;
import io.jmix2mvp.petclinic.graphql.scalar.coercing.BaseDateCoercing;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class OffsetDateTimeScalar {

    public static final String OFFSET_DATE_TIME_FORMAT = "'yyyy-MM-dd'T'HH:mm:ss+hh:mm'";

    public static GraphQLScalarType type =
            (new GraphQLScalarType.Builder())
                    .name("OffsetDateTime")
                    .description("Date type with offset")
                    .coercing(new BaseDateCoercing<OffsetDateTime, String>(OFFSET_DATE_TIME_FORMAT, OffsetDateTime.class){
                        @Override
                        public String doSerialize(OffsetDateTime input) {
                            return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(input);
                        }

                        protected OffsetDateTime parseString(String value) {
                            return OffsetDateTime.from(
                                    Instant.from(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(value))
                                            .atZone(ZoneId.systemDefault()));
                        }
                    }).build();

}
