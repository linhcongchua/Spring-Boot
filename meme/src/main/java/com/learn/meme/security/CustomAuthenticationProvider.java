package com.learn.meme.security;

import com.learn.meme.service.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private PasswordEncoder passwordEncoder;
    private UserService userService;

    public CustomAuthenticationProvider(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(
                    messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }

        String presentedPassword=authentication.getCredentials()
                .toString();

        if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            logger.debug("Authentication failed: password does not match stored value");
            throw new BadCredentialsException(
                    messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        CustomAuthenticationToken auth = (CustomAuthenticationToken) authentication;
        UserDetails loadedUser;

        loadedUser = this.userService.loadUserByUsernameAndDomain(auth.getPrincipal().toString(), auth.getDomain());

        return loadedUser;
    }
}
