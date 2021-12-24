package com.learn.meme.config;


import com.learn.meme.security.CustomAuthenticationProvider;
import com.learn.meme.security.jwt.JwtConfig;
import com.learn.meme.security.jwt.JwtCredentialAuthenticationFilter;
import com.learn.meme.security.jwt.JwtTokenVerifier;
import com.learn.meme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsUtils;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    @Autowired
    public SecurityConfig(UserService userService, PasswordEncoder passwordEncoder, SecretKey secretKey, JwtConfig jwtConfig) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable()
//                .authorizeRequests()
//                .anyRequest().permitAll();
//        http.authorizeRequests().antMatchers("/").permitAll().and()
//                .authorizeRequests().antMatchers("console/**").permitAll();
//
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtCredentialAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtCredentialAuthenticationFilter.class)
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/","/login","product/all").permitAll()
                .antMatchers(HttpMethod.POST,"/user/registration/**").permitAll()
                .anyRequest()
                .authenticated();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider());
    }

    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        CustomAuthenticationProvider provider = new CustomAuthenticationProvider(passwordEncoder, userService);
        return provider;
    }

}
