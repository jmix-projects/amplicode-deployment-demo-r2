package io.jmix2mvp.petclinic;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.execution.DataFetcherResult;
import graphql.schema.GraphQLInputType;
import graphql.schema.GraphQLOutputType;
import io.jmix2mvp.petclinic.graphql.ErrorType;
import io.jmix2mvp.petclinic.graphql.scalar.LocalType;
import io.leangen.graphql.ExtensionProvider;
import io.leangen.graphql.GeneratorConfiguration;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.execution.ResolverInterceptor;
import io.leangen.graphql.execution.ResolverInterceptorFactory;
import io.leangen.graphql.generator.mapping.TypeMapper;
import io.leangen.graphql.generator.mapping.TypeMappingEnvironment;
import io.leangen.graphql.generator.mapping.common.IdAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.AnnotatedType;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Configuration
public class GraphQLConfiguration {


    @Bean
    public ExtensionProvider<GeneratorConfiguration, ResolverInterceptorFactory> resolverInterceptorFactoryProvider() {
        return (config, defaults) -> defaults.append(params -> getResolverInterceptors());
    }

    protected List<ResolverInterceptor> getResolverInterceptors() {
        return Collections.singletonList((context, continuation) -> {
            try {
                return continuation.proceed(context);
            } catch (AccessDeniedException e) {
                GraphQLError error = GraphqlErrorBuilder.newError()
                        .errorType(ErrorType.FORBIDDEN)
                        .message("Forbidden")
                        .build();
                return DataFetcherResult.newResult()
                        .error(error)
                        .build();
            }
        });
    }
}
