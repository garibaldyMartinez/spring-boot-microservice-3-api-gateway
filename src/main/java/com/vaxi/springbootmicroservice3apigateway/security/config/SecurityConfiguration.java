package com.vaxi.springbootmicroservice3apigateway.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.vaxi.springbootmicroservice3apigateway.security.user.Role;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http.cors();
        http.csrf().disable();
        http.authenticationProvider(authenticationProvider);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
            .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/api/v1/auth/register").permitAll()
            .requestMatchers("api/v1/auth/authenticate").permitAll()
            .requestMatchers(HttpMethod.GET, "/gateway/inmueble").permitAll()
            .requestMatchers("/gateway/inmueble/**").hasAuthority(Role.ADMIN.name())
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            );

        return  http.build();
    }
}
