package com.project.holidays.config.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;

@Configuration
class CustomSecurityConfig {
    private static final String EMPLOYEE_ROLE = "EMPLOYEE";
    private static final String TEAM_LEADER_ROLE = "TEAM_LEADER";
    private static final String ADMIN_ROLE = "ADMIN";

    @Resource
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz
//                        .mvcMatchers("/holidays/add").authenticated()
//                        .mvcMatchers("/registration").hasAnyRole(ADMIN_ROLE)
                        .anyRequest().permitAll()
                );
//                .formLogin(login -> login
//                        .loginPage("/login").permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout/**", HttpMethod.GET.name()))
//                        .logoutSuccessUrl("/login?logout").permitAll()
//                );
        http.csrf().ignoringRequestMatchers(new AntPathRequestMatcher("/**"));
        http.csrf().ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"));
//        http.csrf().ignoringRequestMatchers(new AntPathRequestMatcher("/registration"));
        http.headers().frameOptions().sameOrigin();
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().mvcMatchers();
    }

    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}