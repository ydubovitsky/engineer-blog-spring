package ru.ydubovitsky.engineerBlog.security.filter;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.ydubovitsky.engineerBlog.config.VariablesConfig;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtVerifierFilter extends OncePerRequestFilter {

    private final VariablesConfig variablesConfig;


    public JwtVerifierFilter(VariablesConfig variablesConfig) {
        this.variablesConfig = variablesConfig;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (Strings.isNullOrEmpty(authHeader) || !authHeader.startsWith(variablesConfig.getTokenPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.replace("Bearer", "");

        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(variablesConfig.getSecurityKey().getBytes())
                    .parseClaimsJws(token);

            Claims body = claimsJws.getBody();
            String username = body.getSubject();

            var authorities = (List<Map<String, String>>) body.get("authorities");
            List<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                    .map(
                            authority -> new SimpleGrantedAuthority(authority.get("authority")))
                    .collect(Collectors.toList());

            Authentication authentication
                    = new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e) {
            throw new IllegalStateException(e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
