package com.unidadesApi.sic.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    /**
     * Mapea los roles desde el atributo realm_access.roles como SimpleGrantedAuthority.
     */
    private List<GrantedAuthority> mapAuthorities(final Map<String, Object> attributes) {
        @SuppressWarnings("unchecked") final Map<String, Object> realmAccess =
                ((Map<String, Object>)attributes.getOrDefault("realm_access", Collections.emptyMap()));
        @SuppressWarnings("unchecked") final Collection<String> roles =
                ((Collection<String>)realmAccess.getOrDefault("roles", Collections.emptyList()));
        return roles.stream()
                .map(role -> ((GrantedAuthority)new SimpleGrantedAuthority(role)))
                .toList();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        final JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(source -> mapAuthorities(source.getClaims()));
        return converter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        return http.cors(withDefaults())  // Habilita CORS con configuración por defecto
                .csrf(csrf -> csrf.disable())  // Desactiva CSRF si no es necesario
                .authorizeHttpRequests(auth -> auth
                        // Permitir acceso público a los endpoints de Swagger
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/actuator/**").permitAll()
                        // Cualquier otra solicitud requiere autenticación
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                        )
                )
                .build();
    }

    
}
