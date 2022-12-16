package ru.otus.homework13.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.otus.homework13.service.UserService;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Override
    public void configure( WebSecurity web ) {
        web.ignoring().antMatchers( "/" );
    }

    @Override
    public void configure( HttpSecurity http ) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers( "/login").permitAll()
                .and()
                .authorizeRequests().antMatchers( "/books/**" ).authenticated()
                .and()
                // Включает Form-based аутентификацию
                .formLogin()
                .defaultSuccessUrl("/books")
                .and()
                .rememberMe().key("bigSecret").tokenValiditySeconds(60 * 60)
                .and()
                .logout()
                .logoutSuccessUrl("/login")
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    public void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

}
