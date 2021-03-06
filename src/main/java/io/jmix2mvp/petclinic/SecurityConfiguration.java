package io.jmix2mvp.petclinic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import static org.springframework.util.StringUtils.trimLeadingCharacter;
import static org.springframework.util.StringUtils.trimTrailingCharacter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    public static final String NOOP_PREFIX = "{noop}";

    @Autowired
    private AppProperties appProperties;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(User.withUsername(appProperties.getUsers().getAdmin().getUsername())
                        .password(getPassword(appProperties.getUsers().getAdmin().getPassword()))
                        .authorities("ROLE_ADMIN").build());
    }

    private String getPassword(String initialPassword) {
        return initialPassword != null && initialPassword.startsWith(NOOP_PREFIX) ? initialPassword
                : NOOP_PREFIX + initialPassword;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .successHandler((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK))
                .permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .antMatchers("/graphql").permitAll()
                .antMatchers("/graphql/**").permitAll()
                .and()
                .cors()
                .and()
                .csrf().disable();
    }

    @Bean
    public WebMvcConfigurer mvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                CorsConfiguration corsConfiguration = appProperties.getCors();
                if (isConfigured(corsConfiguration)) {
                    registry.addMapping("/**").combine(corsConfiguration);
                }
            }

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                String publicUrl = normalizeUrl(appProperties.getFrontend().getPublicUrl());
                registry.addViewController("/" + publicUrl).setViewName("forward:/" + publicUrl + "/index.html");
            }

            private boolean isConfigured(CorsConfiguration corsConfiguration) {
                return corsConfiguration.getAllowedOrigins() != null && !corsConfiguration.getAllowedOrigins().isEmpty() ||
                        corsConfiguration.getAllowedOriginPatterns() != null && !corsConfiguration.getAllowedOriginPatterns().isEmpty();
            }

            private String normalizeUrl(String url) {
                return trimTrailingCharacter(trimLeadingCharacter(url, '/'), '/');
            }
        };
    }

}
