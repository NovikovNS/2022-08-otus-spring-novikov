package ru.otus.homework13.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, USER, BLOCKED_USER;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
