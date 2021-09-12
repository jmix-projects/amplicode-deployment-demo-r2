package io.jmix2mvp.petclinic;

import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;

@Configuration
public class GraphQLConfiguration {

    @Bean
    public DataFetcherExceptionResolver exceptionResolver() {
        return new DataFetcherExceptionResolverAdapter() {
            @Override
            protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
                return super.resolveToSingleError(ex, env);
            }
        };
    }

}
