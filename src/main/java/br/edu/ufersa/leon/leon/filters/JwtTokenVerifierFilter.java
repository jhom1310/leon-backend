package br.edu.ufersa.leon.leon.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

import static br.edu.ufersa.leon.leon.config.SecurityConfig.AUTH_ROUTE;
import static br.edu.ufersa.leon.leon.filters.JwtAuthenticationFilter.SECRET_KEY;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

public class JwtTokenVerifierFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals(AUTH_ROUTE)) {
            filterChain.doFilter(request, response);
            return;
        }
        var authorization = request.getHeader(AUTHORIZATION);
        if (authorization != null && authorization.startsWith("Bearer ")) {
            var token = authorization.substring("Bearer ".length());
            var algorithm = Algorithm.HMAC256(SECRET_KEY);
            try {
                var decodedJWT = JWT.require(algorithm).build().verify(token);
                var email = decodedJWT.getSubject();
                var authorities = decodedJWT
                        .getClaim("roles").asList(String.class)
                        .stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
                var authenticationToken = new UsernamePasswordAuthenticationToken(email, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(FORBIDDEN.value());
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
