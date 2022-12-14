package com.example.blogappapis.config;


import com.example.blogappapis.security.CustomUserDetailService;
import com.example.blogappapis.security.JwtAuthenticationEntryPoint;
import com.example.blogappapis.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String[] PUBLIC_URLS={
            "/api/v1/auth/**",

    };
    @Autowired
 private CustomUserDetailService customUserDetailService;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .csrf()
               .disable()
               .authorizeHttpRequests()
               .antMatchers("/api/v1/auth/login").permitAll()
               .antMatchers( "/swagger-ui/**","/swagger-resources/**", "/v3/api-docs/**", "/webjars/**").permitAll()
               .anyRequest()
               .authenticated()
               .and()
               .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
               .and()
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       http.addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
               /*csrf()
               .disable()
               .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
               .and()
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
               .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
               .authorizeHttpRequests()
               .antMatchers( "/swagger-ui/**","/swagger-resources/**", "/v3/api-docs/**", "/webjars/**").permitAll()
               .antMatchers(PUBLIC_URLS).permitAll()
               .antMatchers(HttpMethod.GET).permitAll()
               .anyRequest()
               .authenticated();*/



    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
