package io.jmix2mvp.petclinic;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.execution.DataFetcherResult;
import graphql.schema.GraphQLInputType;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLScalarType;
import io.jmix2mvp.petclinic.graphql.ErrorType;
import io.jmix2mvp.petclinic.graphql.scalar.ScalarTypes;
import io.leangen.graphql.ExtensionProvider;
import io.leangen.graphql.GeneratorConfiguration;
import io.leangen.graphql.execution.InvocationContext;
import io.leangen.graphql.execution.ResolverInterceptor;
import io.leangen.graphql.execution.ResolverInterceptorFactory;
import io.leangen.graphql.generator.mapping.SchemaTransformer;
import io.leangen.graphql.generator.mapping.TypeMapper;
import io.leangen.graphql.generator.mapping.TypeMappingEnvironment;
import io.leangen.graphql.generator.mapping.common.ScalarMapper;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import io.leangen.graphql.spqr.spring.modules.data.DefaultValueSchemaTransformer;
import io.leangen.graphql.spqr.spring.modules.data.Order;
import io.leangen.graphql.spqr.spring.modules.data.Pagination;
import io.leangen.graphql.spqr.spring.modules.data.Sorting;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.AnnotatedElement;
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
    public ExtensionProvider<GeneratorConfiguration, ResolverInterceptorFactory> resolverInterceptorFactoryProvider(ApplicationContext applicationContext) {
        return (config, defaults) -> defaults.append(params -> getResolverInterceptors(applicationContext));
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


    //TODO: support custom scalar types (doesn't required for MVP)
    @Bean
    public ExtensionProvider<GeneratorConfiguration, TypeMapper> customTypeMappers() {
        return (config, current) ->
                current.insertBefore(ScalarMapper.class,
                                new TypeMapper() {
                                    @Override
                                    public GraphQLScalarType toGraphQLType(AnnotatedType javaType, Set<Class<? extends TypeMapper>> mappersToSkip, TypeMappingEnvironment env) {
                                        return ScalarTypes.CURRENCY_TYPE;
                                    }

                                    @Override
                                    public GraphQLScalarType toGraphQLInputType(AnnotatedType javaType, Set<Class<? extends TypeMapper>> mappersToSkip, TypeMappingEnvironment env) {
                                        return ScalarTypes.CURRENCY_TYPE;
                                    }

                                    @Override
                                    public boolean supports(AnnotatedElement element, AnnotatedType type) {
                                        return Currency.class.equals(type.getType());
                                    }
                                })
                        .insertBefore(ScalarMapper.class, new TypeMapper() {
                            @Override
                            public GraphQLOutputType toGraphQLType(AnnotatedType javaType, Set<Class<? extends TypeMapper>> mappersToSkip, TypeMappingEnvironment env) {
                                return ScalarTypes.BASE_64_STRING;
                            }

                            @Override
                            public GraphQLInputType toGraphQLInputType(AnnotatedType javaType, Set<Class<? extends TypeMapper>> mappersToSkip, TypeMappingEnvironment env) {
                                return ScalarTypes.BASE_64_STRING;
                            }

                            @Override
                            public boolean supports(AnnotatedElement element, AnnotatedType type) {
                                return Byte[].class.equals(type.getType());
                            }
                        });
    }

    protected List<ResolverInterceptor> getResolverInterceptors(ApplicationContext applicationContext) {
        return Arrays.asList(new AccessDeniedResolverInterceptor(applicationContext), new ValidationInterceptor());
    }

    //TODO: access denied bugfix
    private static class AccessDeniedResolverInterceptor implements ResolverInterceptor {
        private AuthenticationTrustResolver resolver;

        public AccessDeniedResolverInterceptor(ApplicationContext applicationContext) {
            try {
                this.resolver = applicationContext.getBean(AuthenticationTrustResolver.class);
            } catch (NoSuchBeanDefinitionException e) {
                this.resolver = new AuthenticationTrustResolverImpl();
            }
        }


        @Override
        public Object aroundInvoke(InvocationContext context, Continuation continuation) throws Exception {
            try {
                return continuation.proceed(context);
            } catch (AccessDeniedException e) {
                GraphQLError error;
                if (resolver.isAnonymous(SecurityContextHolder.getContext().getAuthentication())) {
                    error = GraphqlErrorBuilder.newError()
                            .errorType(ErrorType.UNAUTHORIZED)
                            .message("Unauthorized")
                            .build();
                } else {
                    error = GraphqlErrorBuilder.newError()
                            .errorType(ErrorType.FORBIDDEN)
                            .message("Forbidden")
                            .build();
                }
                return DataFetcherResult.newResult()
                        .error(error)
                        .build();
            }
        }
    }

    //TODO: Bean validation: Add constraints into GraphQL error (doesn't required for MVP)
    private static class ValidationInterceptor implements ResolverInterceptor {
        @Override
        public Object aroundInvoke(InvocationContext context, Continuation continuation) throws Exception {
            try {
                return continuation.proceed(context);
            } catch (ConstraintViolationException e) {
                GraphQLError error = GraphqlErrorBuilder.newError()
                        .errorType(graphql.ErrorType.ValidationError)
                        .extensions(getExtensions(e.getConstraintViolations()))
                        .message("Bean validation error")
                        .build();
                return DataFetcherResult.newResult()
                        .error(error)
                        .build();
            }
        }

        private Map<String, Object> getExtensions(Set<ConstraintViolation<?>> constraintViolations) {
            Map<String, Object> extensions = new HashMap<>();
            extensions.put("constraintViolations", constraintViolations.stream()
                    .map(this::composeErrorExtension)
                    .collect(Collectors.toList()));

            return extensions;
        }

        private Map<String, Object> composeErrorExtension(ConstraintViolation<?> constraintViolation) {
            Map<String, Object> errorMap = new HashMap<>();

            errorMap.put("messageTemplate", constraintViolation.getMessageTemplate());
            errorMap.put("message", constraintViolation.getMessage());
            errorMap.put("path", composePath(constraintViolation));
            errorMap.put("invalidValue", constraintViolation.getInvalidValue().toString());

            return errorMap;
        }

        private String composePath(ConstraintViolation<?> constraintViolation) {
            GraphQLApi annotation = AnnotationUtils.findAnnotation(constraintViolation.getRootBeanClass(), GraphQLApi.class);
            if (annotation != null) {
                String propertyPath = constraintViolation.getPropertyPath().toString();
                return propertyPath.substring(propertyPath.indexOf(".") + 1);
            } else {
                return constraintViolation.getPropertyPath().toString();
            }
        }
    }
}
