package com.apiGate.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.config.EnableWebFlux;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFlux
public class SecurityConfig {

   // @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        httpSecurity
                .authorizeExchange(exchanges -> exchanges
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwtDecoder())
                );
        return httpSecurity.build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        // You can use the NimbusReactiveJwtDecoder if you have a JWK Set URI
        // return NimbusReactiveJwtDecoder.withJwkSetUri("https://your-jwk-set-uri").build();

        // Otherwise, you can use the default decoder provided by Spring Security
        return ReactiveJwtDecoders.fromOidcIssuerLocation("https://dev-07961356.okta.com/oauth2/default");
    }
}
