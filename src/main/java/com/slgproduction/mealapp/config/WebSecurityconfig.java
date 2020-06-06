package com.slgproduction.mealapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityconfig extends WebSecurityConfigurerAdapter {

    private static final String PREFIX = "/mealapp/v1";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                    .permitAll()
                .antMatchers(PREFIX.concat("/index"),
                            PREFIX.concat("/signup"),
                            PREFIX.concat("/login"))
                .permitAll()
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage(PREFIX.concat("/login"))
                    .permitAll()
                .defaultSuccessUrl(PREFIX.concat("/recipes"))
                .and()
                    .logout()
                    .logoutSuccessUrl(PREFIX.concat("/index"))
                    .permitAll();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

}
