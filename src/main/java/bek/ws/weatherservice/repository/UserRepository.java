package bek.ws.weatherservice.repository;

import bek.ws.weatherservice.entity.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, Integer> {

    Mono<UserEntity> findByUsername(String username);
}
