package io.jmix2mvp.petclinic.graphql.scalar;

import graphql.schema.GraphQLScalarType;
import io.jmix2mvp.petclinic.graphql.scalar.coercing.BaseDateCoercing;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class DateScalar extends GraphQLScalarType {

    public DateScalar() {
        super("Date", "Date type", new BaseDateCoercing<Date, String>(LocalDateScalar.LOCAL_DATE_FORMAT, Date.class) {

            @Override
            public String doSerialize(Date input) {
                return DateTimeFormatter.ISO_LOCAL_DATE.withZone(ZoneId.systemDefault())
                        .format(Instant.ofEpochMilli(input.getTime()));
            }

            protected Date parseString(String value) {
                return java.sql.Date.valueOf(value);
            }
        });
    }

}
