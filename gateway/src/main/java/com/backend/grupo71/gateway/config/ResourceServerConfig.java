package com.backend.grupo71.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.List;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig {
  @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf().disable()
            .authorizeExchange()
            .anyExchange().permitAll();
        return http.build();
    }

  // @Bean
  // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  // http.authorizeHttpRequests(authorize -> authorize
  // // Esta ruta puede ser accedida por cualquiera, sin autorización
  // .requestMatchers(HttpMethod.GET,"/api/v1/pruebas")
  // .hasRole("EMPLEADO")
  // .requestMatchers(HttpMethod.POST,"/api/v1/notifications/mail")
  // .hasRole("EMPLEADO")
  // .requestMatchers("/informe/**")
  // .hasRole("ADMIN")
  // .requestMatchers(HttpMethod.POST,"/api/v1/posiciones")
  // .hasRole("VEHICULO")
  // .anyRequest()
  // .permitAll()
  //
  //
  //
  // ).oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
  // return http.build();
  // }
  //
  // @Bean
  // JwtDecoder jwtDecoder() {
  // return
  // JwtDecoders.fromIssuerLocation("https://labsys.frc.utn.edu.ar/aim/realms/backend-tps");
  // }
  // interface AuthoritiesConverter extends Converter<Map<String, Object>,
  // Collection<GrantedAuthority>> {}
  //
  // @Bean
  // AuthoritiesConverter realmRolesAuthoritiesConverter() {
  // return claims -> {
  // var realmAccess = Optional.ofNullable((Map<String, Object>)
  // claims.get("realm_access"));
  // var roles = realmAccess.flatMap(map -> Optional.ofNullable((List<String>)
  // map.get("roles")));
  // List<GrantedAuthority> list = roles.map(List::stream)
  // .orElse(Stream.empty())
  // .map(SimpleGrantedAuthority::new)
  // .map(GrantedAuthority.class::cast)
  // .toList();
  //
  // return list;
  // };
  // }
  //
  // @Bean
  // JwtAuthenticationConverter authenticationConverter(
  // Converter<Map<String, Object>, Collection<GrantedAuthority>>
  // authoritiesConverter) {
  // var authenticationConverter = new JwtAuthenticationConverter();
  // authenticationConverter.setJwtGrantedAuthoritiesConverter(jwt ->
  // authoritiesConverter.convert(jwt.getClaims()));
  // return authenticationConverter;
  // }

}