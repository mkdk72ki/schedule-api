package com.mkdk.schedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final UserDetailsService userDetailsService;

  public SecurityConfig(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        .formLogin(formLogin -> formLogin
            .loginPage("/login")
            .permitAll()
        )
        .authorizeHttpRequests(authorizeRequests -> authorizeRequests
            .requestMatchers("/login").permitAll()
            .requestMatchers("/users/createForm").permitAll()
            .requestMatchers(antMatcher("/users/admin/**")).hasAuthority("ADMIN")
            .requestMatchers(antMatcher("/groups/admin/**")).hasAuthority("ADMIN")
            .requestMatchers(antMatcher("/schedule/admin/**")).hasAuthority("ADMIN")
            .anyRequest().authenticated()
        );
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
