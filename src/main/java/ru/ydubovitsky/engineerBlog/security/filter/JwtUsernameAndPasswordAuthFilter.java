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
import ru.ydubovitsky.engineerBlog.dto.AppUserDto;
import ru.ydubovitsky.engineerBlog.entity.AppUser;
import ru.ydubovitsky.engineerBlog.security.request.UsernameAndPasswordAuthRequest;
import ru.ydubovitsky.engineerBlog.service.user.AppUserService;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class JwtUsernameAndPasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final AppUserService appUserService;
    private final String key = "org.springframework.boot.devtools.restrt.classloader.RestartClassLoaderorg.springframework.boot.devtools.restart.classloader.RestartClassLoader";

    public JwtUsernameAndPasswordAuthFilter(AuthenticationManager authenticationManager, AppUserService appUserService) {
        this.authenticationManager = authenticationManager;
        this.appUserService = appUserService;
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
    ) throws IOException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(4)))
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .compact();

        //TODO Вынести в отдельный метод?
        response.resetBuffer();
        response.setStatus(HttpStatus.OK.value());
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        response.getWriter().print(
                new ObjectMapper().writeValueAsString(getAuthResponse(authResult, "Bearer " + token))
        );
        response.flushBuffer();
    }

    private AppUserDto getAuthResponse(Authentication authResult, String jwttoken) {
        AppUser appUser = appUserService.getAppUserByName(authResult.getName());

        return AppUserDto.builder()
                .jwttoken(jwttoken)
                .username(appUser.getUsername())
                .about(appUser.getAbout())
                .contacts(appUser.getContacts())
                .firstName(appUser.getFirstName())
                .lastName(appUser.getLastName())
                .roles(appUser.getRoles())
                .build();
    }
}
