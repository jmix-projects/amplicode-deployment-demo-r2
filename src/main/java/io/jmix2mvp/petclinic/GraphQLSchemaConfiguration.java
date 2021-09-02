package io.jmix2mvp.petclinic;

import graphql.schema.GraphQLScalarType;
import io.jmix2mvp.petclinic.graphql.scalar.ScalarTypes;
import io.leangen.graphql.ExtensionProvider;
import io.leangen.graphql.GeneratorConfiguration;
import io.leangen.graphql.generator.mapping.TypeMapper;
import io.leangen.graphql.generator.mapping.TypeMappingEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.AnnotatedType;
import java.util.Locale;
import java.util.Set;

@Configuration
public class GraphQLSchemaConfiguration {

    @Bean
    public ExtensionProvider<GeneratorConfiguration, TypeMapper> customTypeMappers() {
        return (config, current) -> current.append(
                new TypeMapper() {
                    @Override
                    public GraphQLScalarType toGraphQLType(AnnotatedType javaType, Set<Class<? extends TypeMapper>> mappersToSkip, TypeMappingEnvironment env) {
                        return ScalarTypes.LOCALE_TYPE;
                    }

                    @Override
                    public GraphQLScalarType toGraphQLInputType(AnnotatedType javaType, Set<Class<? extends TypeMapper>> mappersToSkip, TypeMappingEnvironment env) {
                        return ScalarTypes.LOCALE_TYPE;
                    }

                    @Override
                    public boolean supports(AnnotatedElement element, AnnotatedType type) {
                        return type.getType() == Locale.class;
                    }
                });
    }

}