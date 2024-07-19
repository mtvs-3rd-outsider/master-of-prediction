package com.outsider.masterofprediction.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Profile("prod")
public class SecurityConfig {


    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http
               .authorizeHttpRequests((auth) -> auth
                       .requestMatchers("/css/**").permitAll()
                       .requestMatchers("/admin-page/login").permitAll()
                       .requestMatchers("/admin-page/**", "/admin-page").hasRole("ADMIN")
                       // .requestMatchers("/", "/login", "/loginProc", "/admin-page/login").permitAll()
                       .requestMatchers("/**").hasAnyRole("ADMIN", "USER")
                       .anyRequest().authenticated()
               );


       http
               .formLogin((auth) -> auth.loginPage("/admin-page/login")
                       .loginProcessingUrl("/admin-loginProc")
                       .permitAll()
               );

       http
               .csrf((auth) -> auth.disable());

       http
               .sessionManagement((auth) -> auth
                       .maximumSessions(1)
                       .maxSessionsPreventsLogin(true));


       http
               .sessionManagement((auth) -> auth
                       .sessionFixation().changeSessionId());
        return http.build();
    }

}
