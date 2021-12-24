package com.learn.meme.security;

import org.apache.commons.compress.utils.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.learn.meme.security.UserPermission.*;

public enum UserRole {
    CUSTOMER(Sets.newHashSet(REQUEST_ORDER, PRODUCT_READ)),
    STAFF(Sets.newHashSet(CONFIRM_ORDER, PRODUCT_WRITE, PRODUCT_READ, USER_READ, USER_WRITE)),
    MANAGER(Sets.newHashSet(MANAGE_STAFF));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> grantedAuthorities =
                getPermissions()
                        .stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                        .collect(Collectors.toSet());
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return grantedAuthorities;
    }
}
