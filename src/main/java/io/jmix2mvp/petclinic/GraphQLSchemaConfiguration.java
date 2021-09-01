package io.jmix2mvp.petclinic;

import graphql.schema.GraphQLInputType;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLScalarType;
import graphql.schema.GraphQLSchemaElement;
import graphql.schema.GraphQLTypeVisitor;
import graphql.util.TraversalControl;
import graphql.util.TraverserContext;
import io.jmix2mvp.petclinic.graphql.scalar.LocalType;
import io.jmix2mvp.petclinic.graphql.scalar.coercing.LocalCoercing;
import io.leangen.graphql.ExtensionProvider;
import io.leangen.graphql.GeneratorConfiguration;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.generator.mapping.TypeMapper;
import io.leangen.graphql.generator.mapping.TypeMappingEnvironment;
import io.leangen.graphql.generator.mapping.common.IdAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.AnnotatedType;
import java.util.Locale;
import java.util.Set;

@Configuration
public class GraphQLSchemaConfiguration {

//    final
//    GraphQLSchemaGenerator graphQLSchemaGenerator;

    final
    GraphQLSchemaGenerator generator;

    public GraphQLSchemaConfiguration(GraphQLSchemaGenerator generator) {
        this.generator = generator;
        this.generator.withTypeMappers(new LocaleType());
    }

    class LocaleType implements TypeMapper {
        @Override
        public GraphQLScalarType toGraphQLType(AnnotatedType javaType, Set<Class<? extends TypeMapper>> mappersToSkip, TypeMappingEnvironment env) {
            return LocalType.getLocal();
        }

        @Override
        public GraphQLScalarType toGraphQLInputType(AnnotatedType javaType, Set<Class<? extends TypeMapper>> mappersToSkip, TypeMappingEnvironment env) {
            return toGraphQLType(javaType, mappersToSkip, env);
        }

        @Override
        public boolean supports(AnnotatedElement element, AnnotatedType type) {
            return type.getType() == Locale.class;
        }
    }



//    public GraphQLSchemaConfiguration(GraphQLSchemaGenerator graphQLSchemaGenerator) {
//        this.graphQLSchemaGenerator = graphQLSchemaGenerator;
//        this.graphQLSchemaGenerator.withTypeMappers((conf, current) ->
//                current.insertAfterOrAppend(IdAdapter.class, new TypeMapper() {
//                    @Override
//                    public GraphQLOutputType toGraphQLType(AnnotatedType javaType, Set<Class<? extends TypeMapper>> mappersToSkip, TypeMappingEnvironment env) {
//                        return LocalType.getLocal();
//                    }
//
//                    @Override
//                    public GraphQLInputType toGraphQLInputType(AnnotatedType javaType, Set<Class<? extends TypeMapper>> mappersToSkip, TypeMappingEnvironment env) {
//                        return LocalType.getLocal();
//                    }
//
//                    @Override
//                    public boolean supports(AnnotatedElement element, AnnotatedType type) {
//                        return type.getType() == Locale.class;
//                    }
//                }));
//    }
}
