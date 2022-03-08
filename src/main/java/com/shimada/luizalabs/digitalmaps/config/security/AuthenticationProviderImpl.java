package com.shimada.luizalabs.digitalmaps.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@PropertySource("application_security.properties")
public class AuthenticationProviderImpl implements AuthenticationProvider {

    @Value("${api_user}")
    private String usernameDefault;
    @Value("${api_password}")
    private String passwordDefault;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final boolean isValid = authentication.getPrincipal().equals(usernameDefault) && authentication.getCredentials().equals(passwordDefault);
        return isValid ?
               new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), null) :
               null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
