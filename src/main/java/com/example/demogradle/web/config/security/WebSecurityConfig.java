package com.example.demogradle.web.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

  private final LoginServiceImpl loginService;
  private final JWTAuthorizationFilter jwtAuthorizationFilter;

  public  WebSecurityConfig(LoginServiceImpl loginService, JWTAuthorizationFilter jwtAuthorizationFilter){
    this.loginService = loginService;
    this.jwtAuthorizationFilter = jwtAuthorizationFilter;
  }

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(userDetailsServic())
        .passwordEncoder(passwordEncoder())
        .and()
        .build();
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {

    JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
    jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
    jwtAuthenticationFilter.setFilterProcessesUrl("/login");

    return http
        .csrf()
        .disable()
        .authorizeRequests()
        /*.antMatchers("/demo/swagger-ui.html", "/demo/swagger-ui/**", "/demo/v3/api-docs/**").permitAll()
        .antMatchers("/demo/h2-console").permitAll()
        .antMatchers("/demo/save/user").permitAll()*/
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilter(jwtAuthenticationFilter)
        .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }


 @Bean
 UserDetailsService userDetailsServic() {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(User.withUsername("admin")
        .password(passwordEncoder().encode("test"))
        .roles()
        .build());
    return manager;
  }


  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
