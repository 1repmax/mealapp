package com.slgproduction.mealapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityconfig extends WebSecurityConfigurerAdapter {

    private static final String PREFIX = "/mealapp/v1";
    private final DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                    .permitAll()
                .antMatchers(PREFIX.concat("/index"),
                            PREFIX.concat("/signup"))
                    .permitAll()
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .defaultSuccessUrl(PREFIX.concat("/recipe/all"))
                    .permitAll()
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl(PREFIX.concat("/index"));
//                .permitAll()
//                .defaultSuccessUrl(PREFIX.concat("/recipes"));
//                    .loginPage(PREFIX.concat("/login"))
//                    .permitAll()
//                .defaultSuccessUrl(PREFIX.concat("/recipes"))
//                .and()
//                    .logout()
//                    .logoutSuccessUrl(PREFIX.concat("/index"))
//                    .permitAll();
    }
//
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select name, password, enabled from users where name = ?")
                .authoritiesByUsernameQuery("select u.name, a.user_role from authorities a\n" +
                        "inner join users u on a.id = u.authority_id\n" +
                        "where u.name = ?");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
