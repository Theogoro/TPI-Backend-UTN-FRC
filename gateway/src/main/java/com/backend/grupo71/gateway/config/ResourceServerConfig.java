package com.backend.grupo71.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.List;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(authorize -> authorize
//                // Esta ruta puede ser accedida por cualquiera, sin autorización
//                .requestMatchers("/publico/**")
//                .permitAll()
//
//                // Esta ruta puede ser accedida únicamente por usuarios autenticados
//                .requestMatchers("/informe/**")
//                .hasRole("ADMIN")
//
//
//
//        ).oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
//        return http.build();
//    }
//
//    interface AuthoritiesConverter extends Converter<Map<String, Object>, Collection<GrantedAuthority>> {}
//
//    @Bean
//    AuthoritiesConverter realmRolesAuthoritiesConverter() {
//        return claims -> {
//            var realmAccess = Optional.ofNullable((Map<String, Object>) claims.get("realm_access"));
//            var roles = realmAccess.flatMap(map -> Optional.ofNullable((List<String>) map.get("roles")));
//            List<GrantedAuthority> list = roles.map(List::stream)
//                    .orElse(Stream.empty())
//                    .map(SimpleGrantedAuthority::new)
//                    .map(GrantedAuthority.class::cast)
//                    .toList();
//
//            return list;
//        };
//    }
//
//    @Bean
//    JwtAuthenticationConverter authenticationConverter(
//            Converter<Map<String, Object>, Collection<GrantedAuthority>> authoritiesConverter) {
//        var authenticationConverter = new JwtAuthenticationConverter();
//        authenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
//            return authoritiesConverter.convert(jwt.getClaims());
//        });
//        return authenticationConverter;
//    }

//    @Bean
//    public JwtAuthenticationConverter jwtAuthenticationConverter() {
//        var jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        var grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//
//        // Se especifica el nombre del claim a analizar
//        grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
//        // Se agrega este prefijo en la conversión por una convención de Spring
//        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
//
//        // Se asocia el conversor de Authorities al Bean que convierte el token JWT a un objeto Authorization
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
//        // También se puede cambiar el claim que corresponde al nombre que luego se utilizará en el objeto
//        // Authorization
//        // jwtAuthenticationConverter.setPrincipalClaimName("user_name");
//
//        return jwtAuthenticationConverter;
//    }
}