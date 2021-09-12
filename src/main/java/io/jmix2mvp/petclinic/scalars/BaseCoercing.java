package io.jmix2mvp.petclinic.scalars;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BaseCoercing implements Coercing<Object, Object> {
    private final List<Class<?>> supportedTypes;

    public BaseCoercing(Class<?>... supportedTypes) {
        this.supportedTypes = Arrays.asList(supportedTypes);
    }

    @Override
    public Object serialize(@NonNull Object result) throws CoercingSerializeException {
        for (Class<?> supportedType : supportedTypes) {
            if (supportedType.isInstance(result)) {
                return supportedType.cast(result);
            }
        }
        throw new CoercingSerializeException(String.format("Expected types '%s' but was '%s'",
                getExpectedTypeNames(), getTypeName(result)));
    }

    @Override
    @NonNull
    public Object parseValue(@NonNull Object input) throws CoercingParseValueException {
        if (input instanceof String) {
            return input;
        } else {
            throw new CoercingParseLiteralException(String.format("Expected type 'String' but was '%s'",
                    getTypeName(input)));
        }
    }

    @Override
    public Object parseLiteral(@NonNull Object input) throws CoercingParseLiteralException {
        if (input instanceof StringValue) {
            return ((StringValue) input).getValue();
        } else {
            throw new CoercingParseLiteralException(String.format("Expected AST type 'StringValue' but was '%s'",
                    getTypeName(input)));
        }
    }

    private String getExpectedTypeNames() {
        return supportedTypes.stream()
                .map(Class::getSimpleName)
                .collect(Collectors.joining(","));
    }

    private static String getTypeName(Object input) {
        if (input == null) {
            return "null";
        }
        return input.getClass().getSimpleName();
    }
}
