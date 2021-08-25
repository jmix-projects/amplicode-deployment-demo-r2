package io.jmix2mvp.petclinic.graphql.scalar;

import graphql.schema.GraphQLScalarType;
import io.jmix2mvp.petclinic.graphql.scalar.coercing.BaseDateCoercing;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateScalar extends GraphQLScalarType {

    public static final String LOCAL_DATE_FORMAT = "'yyyy-MM-dd'";

    public LocalDateScalar() {
        super("LocalDate", "Local date type", new BaseDateCoercing<LocalDate, String>(LOCAL_DATE_FORMAT, LocalDate.class) {

            @Override
            public String doSerialize(LocalDate input) {
                return DateTimeFormatter.ISO_LOCAL_DATE.format(input);
            }

            protected LocalDate parseString(String value) {
                return LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(value));
            }
        });
    }

}
