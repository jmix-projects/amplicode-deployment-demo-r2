package io.jmix2mvp.petclinic.graphql.scalar;

import graphql.schema.GraphQLScalarType;
import io.jmix2mvp.petclinic.graphql.scalar.coercing.BaseDateCoercing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeScalar {

    public static final String LOCAL_DATE_TIME_FORMAT = "'yyyy-MM-dd'T'HH:mm:ss'";

    public static GraphQLScalarType type =
            (new GraphQLScalarType.Builder())
                    .name("LocalDateTime")
                    .description("Local date type with time")
                    .coercing(new BaseDateCoercing<LocalDateTime, String>(LOCAL_DATE_TIME_FORMAT, LocalDateTime.class) {

                        @Override
                        public String doSerialize(LocalDateTime input) {
                            return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(input)+" Test";
                        }

                        protected LocalDateTime parseString(String value) {
                            return LocalDateTime.from(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(value));
                        }
                    }).build();

}
