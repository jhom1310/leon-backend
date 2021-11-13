package br.edu.ufersa.leon.leon.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    protected static final String SECRET_KEY = "super-secret-password";
    public static final String ACCESS_TOKEN_HEADER = "access_token";
    public static final String REFRESH_TOKEN_HEADER = "refresh_token";
    private static final long ACCESS_TOKEN_MINUTES = 10;
    private static final long REFRESH_TOKEN_MINUTES = 1 * 24 * 60;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        var credentials = parseCredentials(request);
        var token = new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword());
        return authenticationManager.authenticate(token);
    }

    private AuthCredentials parseCredentials(HttpServletRequest request) {
        try {
            var credentials = new ObjectMapper()
                    .readValue(request.getInputStream(), AuthCredentials.class);
            if (Strings.isEmpty(credentials.getEmail()) || Strings.isEmpty(credentials.getPassword())) {
                throw new BadCredentialsException("Invalid credentials");
            }
            return credentials;
        } catch (IOException e) {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        var user = (User) authResult.getPrincipal();
        var algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
        var accessToken = createToken(request, user, algorithm, ACCESS_TOKEN_MINUTES);
        var refreshToken = createToken(request, user, algorithm, REFRESH_TOKEN_MINUTES);
        var tokens = Map.of(ACCESS_TOKEN_HEADER, accessToken, REFRESH_TOKEN_HEADER, refreshToken);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    private String createToken(HttpServletRequest request, User user, Algorithm algorithm, long minutes) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(Date.from(LocalDateTime.now().plusMinutes(minutes).toInstant(ZoneOffset.ofHours(-3))))
                .withIssuer(request.getRequestURI())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }
}

@Data
class AuthCredentials {
    private String email;
    private String password;
}
