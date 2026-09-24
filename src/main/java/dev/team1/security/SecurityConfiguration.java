package dev.team1.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration 
@EnableWebSecurity 
@RequiredArgsConstructor 
@EnableMethodSecurity 
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;

    @Value("/${api-endpoint}")
    private String pre;

    @Bean 
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configuration without auth and security
        //
        // http
        //     .csrf(csrf -> csrf.disable())
        //     .authorizeHttpRequests(auth -> auth
        //         .anyRequest().permitAll()
        //     );
        // return http.build();


        return http
            .httpBasic(AbstractHttpConfigurer::disable)
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(pre + "/users").hasRole("ADMIN")
                .requestMatchers(pre + "/products/administration").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, pre + "/orders/**").hasAnyAuthority("ROLE_COOK", "ROLE_DELIVERYMAN")
                .requestMatchers(pre + "/auth/login").permitAll()
                .requestMatchers(pre + "/auth/refresh").permitAll()
                .requestMatchers(HttpMethod.GET, pre + "/products").permitAll()
                .requestMatchers(HttpMethod.POST, pre + "/users").permitAll()
                .anyRequest().authenticated())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173")); // frontend 
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true); 

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
