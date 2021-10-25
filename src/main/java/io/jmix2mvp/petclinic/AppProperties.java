package io.jmix2mvp.petclinic;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private final CorsConfiguration cors = new CorsConfiguration();
    private final FrontendProperties frontend = new FrontendProperties();

    public CorsConfiguration getCors() {
        return cors;
    }

    public FrontendProperties getFrontend() {
        return frontend;
    }

    public static class FrontendProperties {
        private String publicUrl = "front";

        public String getPublicUrl() {
            return publicUrl;
        }

        public void setPublicUrl(String publicUrl) {
            this.publicUrl = publicUrl;
        }
    }
}
