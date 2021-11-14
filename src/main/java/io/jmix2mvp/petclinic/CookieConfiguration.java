package io.jmix2mvp.petclinic;

import graphql.com.google.common.base.Strings;
import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CookieConfiguration {
    @Autowired
    private AppProperties appProperties;

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> cookieProcessorCustomizer() {
        return factory -> {
            if (!Strings.isNullOrEmpty(appProperties.getCookie().getSameSite())) {
                Rfc6265CookieProcessor cookieProcessor = new Rfc6265CookieProcessor();
                cookieProcessor.setSameSiteCookies(appProperties.getCookie().getSameSite());
                factory.addContextCustomizers((context) -> context.setCookieProcessor(cookieProcessor));
            }
        };
    }
}
