package com.admin.adminapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final String[] AUTH_WHITELIST = {
            "/v2/api-docs/**", "/configuration/ui/**", "/swagger-resources/**",
            "/configuration/security/**", "/swagger-ui.html/**", "/webjars/**"
    };

    private final DataSource dataSource;


    @Autowired
    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .failureHandler(((request, response, exception) -> response.sendError(HttpServletResponse.SC_FORBIDDEN)))
                .successHandler(((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK)))
                .and()
                .logout().logoutUrl("/logout")
                .and().exceptionHandling()
                .defaultAuthenticationEntryPointFor(
                        ((request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied. Login at: /api/login")),
                        request -> !request.getRequestURI().contains("login"));
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "SELECT username, password, enabled FROM Admin WHERE username = ?")
                .authoritiesByUsernameQuery(
                        "SELECT username, role from Admin WHERE username = ?");
    }

}