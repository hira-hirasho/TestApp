package com.example.testapp.config;

import com.example.testapp.service.StudentDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@Order(1)
public class StudentSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private final StudentDetailsService studentDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/js/**", "/css/**", "/img/**", "/webjars**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/student/**")
                .authorizeRequests()
                .antMatchers("/student/login", "/student/register/**")
                .permitAll()
                .antMatchers("/student/**")
                .hasRole("STUDENT")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/student/login")
                .defaultSuccessUrl("/student/course")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/student/logout"));
        // .and()
        // .rememberMe();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(studentDetailsService).passwordEncoder(passwordEncoder());
    }
}
