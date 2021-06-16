package com.monolithical.carservice.configuration;

import com.monolithical.carservice.api.authentication.JWTFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class to configure Spring Security for use with JWT-based access validation
 */
@Configuration
@EnableWebSecurity
public class JWTFilterWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private final JWTFilter jwtFilter;

    public JWTFilterWebSecurityConfigurerAdapter(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        super.configure(httpSecurity);
    }
}
