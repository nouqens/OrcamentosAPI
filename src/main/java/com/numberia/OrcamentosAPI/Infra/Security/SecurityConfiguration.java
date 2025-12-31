package com.numberia.OrcamentosAPI.Infra.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/orcamentos/usuarios/admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/orcamentos/usuarios/me").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/orcamentos/clientes/name/{name}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "orcamentos/clientes/{id}").hasAnyRole("USER", "ADMIN ")
                        .requestMatchers(HttpMethod.GET, "/orcamentos/obras/usuario/{id}").hasAnyRole("USER", "ADMIN")//hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/orcamentos/recursos/{id}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/orcamentos/usuarios/clientes/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/orcamentos/clientes").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/orcamentos/obras/usuario/preco/{id}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/orcamentos/recursos/{id}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "orcamentos/recursos").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/orcamentos/clientes").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/orcamentos/usuarios/admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/orcamentos/obras").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/orcamentos/usuarios").permitAll()
                        .requestMatchers(HttpMethod.POST, "/orcamentos/auth/login").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/orcamentos/usuarios").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )

                        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration){
        try{
            return authenticationConfiguration.getAuthenticationManager();
        } catch (Exception e) {
            throw new RuntimeException("Falha na Autenticação!");
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder() ;
    }
}
