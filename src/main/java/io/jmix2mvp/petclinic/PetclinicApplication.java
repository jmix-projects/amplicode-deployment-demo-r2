package io.jmix2mvp.petclinic;

import graphql.schema.GraphQLInputType;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLScalarType;
import graphql.schema.GraphQLSchemaElement;
import graphql.schema.GraphQLTypeVisitor;
import graphql.util.TraversalControl;
import graphql.util.TraverserContext;
import io.jmix2mvp.petclinic.graphql.scalar.CurrencyScalar;
import io.jmix2mvp.petclinic.graphql.scalar.LocalDateTimeScalar;
import io.leangen.graphql.ExtensionProvider;
import io.leangen.graphql.GeneratorConfiguration;
import io.leangen.graphql.execution.GlobalEnvironment;
import io.leangen.graphql.execution.ResolutionEnvironment;
import io.leangen.graphql.generator.OperationMapper;
import io.leangen.graphql.generator.mapping.InputConverter;
import io.leangen.graphql.generator.mapping.OutputConverter;
import io.leangen.graphql.generator.mapping.TypeMapper;
import io.leangen.graphql.generator.mapping.TypeMappingEnvironment;
import io.leangen.graphql.generator.mapping.common.IdAdapter;
import io.leangen.graphql.metadata.strategy.value.ValueMapper;
import io.leangen.graphql.metadata.strategy.value.ValueMapperFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Set;

@SpringBootApplication
public class PetclinicApplication {

//    @Bean
//    public ExtensionProvider<GeneratorConfiguration, TypeMapper> customTypeMappers() {
//        //Insert a custom mapper after the built-in IdAdapter (which is generally a safe position)
//        return (config, current) -> current.insertAfter(IdAdapter.class,
//                new TypeMapper() {
//                    @Override
//                    public GraphQLOutputType toGraphQLType(AnnotatedType javaType, Set<Class<? extends TypeMapper>> mappersToSkip, TypeMappingEnvironment env) {
//                        return new CurrencyScalar();
//                    }
//
//                    @Override
//                    public GraphQLInputType toGraphQLInputType(AnnotatedType javaType, Set<Class<? extends TypeMapper>> mappersToSkip, TypeMappingEnvironment env) {
//                        return new CurrencyScalar();
//                    }
//
//                    @Override
//                    public boolean supports(AnnotatedElement element, AnnotatedType type) {
//                        return type.getType() == Currency.class;
//                    }
//
//                }
//        );
//    }

    public static void main(String[] args) {
        SpringApplication.run(PetclinicApplication.class, args);
    }
}
