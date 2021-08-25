package io.jmix2mvp.petclinic;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.execution.DataFetcherResult;
import io.jmix2mvp.petclinic.graphql.ErrorType;
import io.leangen.graphql.ExtensionProvider;
import io.leangen.graphql.GeneratorConfiguration;
import io.leangen.graphql.execution.ResolverInterceptor;
import io.leangen.graphql.execution.ResolverInterceptorFactory;
import io.leangen.graphql.generator.mapping.SchemaTransformer;
import io.leangen.graphql.spqr.spring.modules.data.DefaultValueSchemaTransformer;
import io.leangen.graphql.spqr.spring.modules.data.Order;
import io.leangen.graphql.spqr.spring.modules.data.Pagination;
import io.leangen.graphql.spqr.spring.modules.data.Sorting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;

import java.lang.reflect.AnnotatedType;
import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class GraphQLConfiguration {
    private static final Set<Class<?>> SUPPORTED_CLASSES = new HashSet<>(Arrays.asList(
            Pageable.class, PageRequest.class, AbstractPageRequest.class, Pageable.unpaged().getClass()
    ));

    //TODO: access denied bugfix
    @Bean
    public ExtensionProvider<GeneratorConfiguration, ResolverInterceptorFactory> resolverInterceptorFactoryProvider() {
        return (config, defaults) -> defaults.append(params -> getResolverInterceptors());
    }

    //TODO: pageable bugfix
    @Bean
    public ExtensionProvider<GeneratorConfiguration, SchemaTransformer> sortingSchemaTransformer() {
        return (config, current) -> current.prepend(
                new DefaultValueSchemaTransformer() {
                    @Override
                    public Object getDefaultValue() {
                        return new Sorting();
                    }

                    @Override
                    public boolean supports(AnnotatedType type) {
                        return Sort.class.equals(type.getType());
                    }
                }
        ).prepend(new DefaultValueSchemaTransformer() {
            @Override
            public Object getDefaultValue() {
                PageRequest pageRequest = PageRequest.of(0, 10);
                Pagination pagination = new Pagination();
                pagination.pageNumber = pageRequest.getPageNumber();
                pagination.pageSize = pageRequest.getPageSize();
                pagination.sort = new Sorting();
                pagination.sort.orders = pageRequest.getSort().get().map(this::mapOrder).collect(Collectors.toList());

                return pagination;
            }

            @Override
            public boolean supports(AnnotatedType type) {
                //noinspection SuspiciousMethodCalls
                return SUPPORTED_CLASSES.contains(type.getType());
            }

            protected Order mapOrder(Sort.Order order) {
                Order result = new Order();
                result.direction = order.getDirection();
                result.property = order.getProperty();
                result.nullHandlingHint = order.getNullHandling();
                result.ignoreCase = order.isIgnoreCase();

                return result;
            }
        });
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
