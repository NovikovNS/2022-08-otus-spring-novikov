package ru.otus.homework13.security;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(
        securedEnabled = true)
public class MethodSecurityConfiguration {
}
