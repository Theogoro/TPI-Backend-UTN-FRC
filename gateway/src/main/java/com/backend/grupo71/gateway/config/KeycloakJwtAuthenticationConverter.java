package com.backend.grupo71.gateway.config;


import jakarta.annotation.Nonnull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class KeycloakJwtAuthenticationConverter implements Converter<Jwt, Mono<JwtAuthenticationToken>> {

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        var realmAccess = jwt.getClaimAsMap("realm_access");
        var roles = (List<String>) realmAccess.get("roles");
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.replace("-", "_"))) // Agregar el prefijo "ROLE_"
                .collect(Collectors.toSet());
    }

    @Override
    public Mono<JwtAuthenticationToken> convert(Jwt source) {
        // Extraer roles desde el JWT
        Collection<? extends GrantedAuthority> authorities = extractResourceRoles(source);

        // Crear el token de autenticación con las autoridades
        JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(source, authorities);

        return Mono.just(authenticationToken);
    }

}
