package bek.ws.weatherservice.security;

import bek.ws.weatherservice.entity.UserEntity;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthManager implements ReactiveAuthenticationManager {

    private final JwtService jwtService;
    private final Gson gson;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        String userJson = jwtService.username(token);

        if (userJson != null && jwtService.validateToken(token)){

            UserEntity user = gson.fromJson(jwtService.getClaim(token, "sub", String.class), UserEntity.class);
            UsernamePasswordAuthenticationToken authenticatedUser = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            return Mono.just(authenticatedUser);
        }else {
            return Mono.empty();
        }
    }
}