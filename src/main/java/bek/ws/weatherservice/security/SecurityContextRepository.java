package bek.ws.weatherservice.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class SecurityContextRepository implements ServerSecurityContextRepository {

    @Autowired
    private AuthManager authManager;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return null;
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        String token = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.replace("Bearer ", "");
        } else {
            log.warn("Authenticate error authorization header not correct");
        }

        if (token != null) {
            return authManager.authenticate(new UsernamePasswordAuthenticationToken(token, token))
                    .doOnNext(ReactiveSecurityContextHolder::withAuthentication)
                    .map(SecurityContextImpl::new);

        } else {
            return Mono.empty();
        }
    }
}
