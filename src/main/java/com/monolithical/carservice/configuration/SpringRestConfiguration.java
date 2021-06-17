package com.monolithical.carservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class SpringRestConfiguration implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        // configure not to use HAL specification, just JSON. exchanges _embedded property to content
        config.setDefaultMediaType(MediaType.APPLICATION_JSON);
        config.useHalAsDefaultJsonMediaType(false);
    }
}
