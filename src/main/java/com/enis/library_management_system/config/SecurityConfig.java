package com.enis.library_management_system.config;

import com.enis.library_management_system.security.JwtAuthenticationFilter;
import com.enis.library_management_system.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
                          CustomUserDetailsService userDetailsService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/api-docs",
                        "/api-docs/**",
                        "/webjars/**",
                        "/swagger-resources/**"
                        ).permitAll()
                        .requestMatchers("/api/auth/**").permitAll()

                        // 3) User management
                        .requestMatchers(HttpMethod.GET,  "/api/users").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.POST, "/api/users").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("LIBRARIAN")

                        // 4) Book management
                        .requestMatchers(HttpMethod.POST,   "/api/books").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.PUT,    "/api/books/**").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.DELETE, "/api/books/**").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.GET,    "/api/books/**").authenticated()

                        // 5) Borrowing management  (controller mapping: /api/borrow)
                        .requestMatchers(HttpMethod.GET,  "/api/borrow/overdue").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.GET,  "/api/borrow/history/all").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.GET,  "/api/borrow/history").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/borrow").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/borrow/return").authenticated()

                        // 6) Diğer tüm istekler auth gerektirir
                        .anyRequest().authenticated()
                )
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
