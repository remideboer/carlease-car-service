package com.monolithical.carservice.api.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Custom filter that validates token and sets Security Context
 */
@Component
public class JWTFilter extends OncePerRequestFilter {

    private static Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
    private final String errorUnauthorizedFormat = "{\"timestamp\":\"%s\", \"message\":\"Unauthorized: %s\"}";
    private final JWTValidator validator;


    public JWTFilter(JWTValidator validator) {
        this.validator = validator;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader("Authorization");
        if (header != null) {
            String[] content = httpServletRequest.getHeader("Authorization").split(" ");

            if (content[0].equals("Bearer") && validator.isValid(content[1])) {
                // is valid then set Springs Security Context
                logger.debug("TOKEN VALID");
                var authentication = new JWTAuthentication();
                authentication.setAuthenticated(true);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(httpServletRequest, response);
            } else {
                logger.debug("TOKEN NOT VALID");
                SecurityContextHolder.clearContext();
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().print(String.format(errorUnauthorizedFormat, LocalDateTime.now(), "The token is not valid."));
            }
        } else {
            logger.debug("HEADER MISSING");
            SecurityContextHolder.clearContext();
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print(String.format(errorUnauthorizedFormat, LocalDateTime.now(), "Authorization header is missing"));
        }
    }
}
