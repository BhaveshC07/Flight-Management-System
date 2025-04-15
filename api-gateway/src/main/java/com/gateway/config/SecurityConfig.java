package com.gateway.config;

import com.gateway.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod; // ✅ Correct HttpMethod import
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        System.out.println("Enter in SecurityWebFilterChain");

        return http.authorizeExchange(exchange -> exchange
                        .pathMatchers("/UserManagement/register",
                                      "/UserManagement/users",
                                      "/UserManagement/login").permitAll()
                        .pathMatchers(HttpMethod.POST, "/fare/**").hasRole("OWNER")
                        .pathMatchers(HttpMethod.GET, "/fare/**").hasAnyRole("USER", "OWNER")
//                        .pathMatchers(HttpMethod.POST, "/flights").hasRole("OWNER")
                        .pathMatchers(HttpMethod.POST, "/search/**").hasRole("OWNER")
                        .pathMatchers(HttpMethod.GET, "/search/**").hasAnyRole("USER", "OWNER")
                        .pathMatchers("/booking/**").hasRole("USER")
		                .pathMatchers("/checkin/**").hasRole("USER")
                        // .pathMatchers("/guest/**").hasRole("OWNER")
                        .anyExchange().denyAll())
                .addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .csrf(csrfSpec -> csrfSpec.disable())
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .build();
    }
}
