package io.jmix2mvp.petclinic.graphql.scalar;

import graphql.schema.GraphQLScalarType;
import io.jmix2mvp.petclinic.graphql.scalar.coercing.BaseDateCoercing;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class DateTimeScalar {

    public static GraphQLScalarType type =
         (new GraphQLScalarType.Builder())
                .name("DateTime")
                .description("Date type with time")
                .coercing(new BaseDateCoercing<Date, String>(LocalDateTimeScalar.LOCAL_DATE_TIME_FORMAT, Date.class) {

                    @Override
                    public String doSerialize(Date input) {
                        // todo move formats to constant class
                        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(input);
                    }

                    @Override
                    protected Date parseString(String value) {
                        LocalDateTime localDateTime = DateTimeFormatter.ISO_DATE_TIME.parse(value.trim(), LocalDateTime::from);
                        return Timestamp.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                    }
                }).build();

}
