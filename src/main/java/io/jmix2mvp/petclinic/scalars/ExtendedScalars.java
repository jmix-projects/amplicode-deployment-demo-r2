package io.jmix2mvp.petclinic.scalars;

import graphql.schema.GraphQLScalarType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.*;

public interface ExtendedScalars {
    GraphQLScalarType DATE = GraphQLScalarType.newScalar()
            .name("Date")
            .coercing(new BaseCoercing(LocalDate.class))
            .build();

    GraphQLScalarType DATETIME = GraphQLScalarType.newScalar()
            .name("DateTime")
            .coercing(new BaseCoercing(LocalDateTime.class, OffsetDateTime.class, ZonedDateTime.class))
            .build();

    GraphQLScalarType TIME = GraphQLScalarType.newScalar()
            .name("Time")
            .coercing(new BaseCoercing(LocalTime.class, OffsetTime.class))
            .build();

    GraphQLScalarType BIGINTEGER = GraphQLScalarType.newScalar()
            .name("BigInteger")
            .coercing(new BaseCoercing(BigInteger.class, Long.class, long.class))
            .build();

    GraphQLScalarType BIG_DECIMAL = GraphQLScalarType.newScalar()
            .name("BigDecimal")
            .coercing(new BaseCoercing(BigDecimal.class))
            .build();
}
