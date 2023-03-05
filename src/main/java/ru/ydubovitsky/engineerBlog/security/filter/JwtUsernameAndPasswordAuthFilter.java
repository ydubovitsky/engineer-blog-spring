package ru.ydubovitsky.engineerBlog.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.ydubovitsky.engineerBlog.config.VariablesConfig;
import ru.ydubovitsky.engineerBlog.security.request.AuthResponse;
import ru.ydubovitsky.engineerBlog.security.request.UsernameAndPasswordAuthRequest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class JwtUsernameAndPasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final VariablesConfig variablesConfig;

    public JwtUsernameAndPasswordAuthFilter(AuthenticationManager authenticationManager, VariablesConfig variablesConfig) {
        this.authenticationManager = authenticationManager;
        this.variablesConfig = variablesConfig;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {
        try {
            UsernameAndPasswordAuthRequest req = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordAuthRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    req.getUsername(),
                    req.getPassword(),
                    null);

            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(4)))
                .signWith(Keys.hmacShaKeyFor(variablesConfig.getSecurityKey().getBytes()))
                .compact();

        response.addHeader("Authorization", "Bearer " + token); //TODO Убрать это?
        //! Возвращаем еще токен и в теле ответа
        response.resetBuffer();
        response.setStatus(HttpStatus.OK.value());
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        response.getOutputStream().print(
                new ObjectMapper().writeValueAsString(
                        new AuthResponse(variablesConfig.getTokenPrefix() + token, authResult.getName())
                )
        );
        response.flushBuffer();
    }
}
