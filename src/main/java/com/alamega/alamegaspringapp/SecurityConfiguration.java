package com.alamega.alamegaspringapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                //Пускать только админов
                .antMatchers("/admin*").hasRole( "ADMIN")
                //Пускать только авторизированных
                .antMatchers("/user.html").authenticated()
                //Пускать всех
                .antMatchers("/**").permitAll()
            .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
            .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll();
        return http.build();
    }
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager()
    {
        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(
            User.withDefaultPasswordEncoder()
            .username("ADMIN")
            .password("ADMIN")
            .roles("ADMIN").build()
        );
        userDetailsList.add(
            User.withDefaultPasswordEncoder()
            .username("USER")
            .password("USER")
            .roles("USER").build()
        );
        return new InMemoryUserDetailsManager(userDetailsList);
    }
}