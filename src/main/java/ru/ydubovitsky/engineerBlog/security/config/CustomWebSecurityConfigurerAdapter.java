package ru.ydubovitsky.engineerBlog.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import ru.ydubovitsky.engineerBlog.security.filter.JwtUsernameAndPasswordAuthFilter;
import ru.ydubovitsky.engineerBlog.security.filter.JwtVerifierFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(new JwtUsernameAndPasswordAuthFilter(authenticationManager()));
        http.addFilterAfter(new JwtVerifierFilter(), JwtUsernameAndPasswordAuthFilter.class);
//        http.authorizeRequests().anyRequest().authenticated();
    }
}
