package com.learn.meme.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private String domain;

    public CustomAuthenticationToken(Object principal, Object credentials, String domain) {
        super(principal, credentials);
        this.domain = domain;
        super.setAuthenticated(false);
    }

    public CustomAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String domain) {
        super(principal, credentials, authorities);
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
