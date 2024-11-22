package com.backend.grupo71.gateway.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {

  @Autowired
  private KeycloakJwtAuthenticationConverter keycloakJwtAuthenticationConverter;


  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) throws Exception {
    http
            .authorizeExchange(authorize -> authorize
                    // Rutas accesibles por empleados
                    .pathMatchers(HttpMethod.GET, "/api/v1/pruebas/").hasRole("EMPLEADO")
                    .pathMatchers("/api/v1/notifications/mail").hasRole("EMPLEADO")
                    // Rutas accesibles por admin
                    .pathMatchers("/api/v1/pruebas/informe/**").hasRole("ADMIN")
                    .pathMatchers("/api/v1/posiciones/informe/**").hasRole("ADMIN")
                    // Rutas accesibles por vehículos
                    .pathMatchers("/api/v1/posiciones").hasRole("VEHICULO")
                    .anyExchange().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(keycloakJwtAuthenticationConverter)));
    return http.build();

  }

//  interface AuthoritiesConverter extends Converter<Map<String, Object>, Collection<GrantedAuthority>> {
//  }

//  @Bean
//  AuthoritiesConverter realmRolesAuthoritiesConverter() {
//    return claims -> {
//      var realmAccess = Optional.ofNullable((Map<String, Object>) claims.get("realm_access"));
//      var roles = realmAccess.flatMap(map -> Optional.ofNullable((List<String>) map.get("roles")));
//      List<GrantedAuthority> list = roles.map(List::stream)
//              .orElse(Stream.empty())
//              .map(SimpleGrantedAuthority::new)
//              .map(GrantedAuthority.class::cast)
//              .toList();
//
//      return list;
//    };
//  }
//
//  @Bean
//  JwtAuthenticationConverter authenticationConverter(
//          Converter<Map<String, Object>, Collection<GrantedAuthority>> authoritiesConverter) {
//    var authenticationConverter = new JwtAuthenticationConverter();
//    authenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
//      return authoritiesConverter.convert(jwt.getClaims());
//    });
//    return authenticationConverter;
//  }

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
//        jwtAuthenticationConverter.setPrincipalClaimName("user_name");
//
//        return jwtAuthenticationConverter;
//    }

}