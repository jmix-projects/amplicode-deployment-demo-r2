package io.jmix2mvp.petclinic.graphql.scalar;

import graphql.schema.GraphQLInputType;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLScalarType;
import io.leangen.graphql.generator.mapping.TypeMapper;
import io.leangen.graphql.generator.mapping.TypeMappingEnvironment;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.AnnotatedType;
import java.util.Currency;
import java.util.Set;

public class CurrencyTypeMapper implements TypeMapper {

    private static final GraphQLScalarType type = ScalarTypes.CURRENCY_TYPE;

    @Override
    public GraphQLOutputType toGraphQLType(AnnotatedType javaType, Set<Class<? extends TypeMapper>> mappersToSkip, TypeMappingEnvironment env) {
        return type;
    }

    @Override
    public GraphQLInputType toGraphQLInputType(AnnotatedType javaType, Set<Class<? extends TypeMapper>> mappersToSkip, TypeMappingEnvironment env) {
        return type;
    }

    @Override
    public boolean supports(AnnotatedElement element, AnnotatedType type) {
        return type.getType() == Currency.class;
    }
}
