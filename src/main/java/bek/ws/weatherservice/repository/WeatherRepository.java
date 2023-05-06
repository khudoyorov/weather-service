package bek.ws.weatherservice.repository;

import bek.ws.weatherservice.entity.WeatherEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Repository
public interface WeatherRepository extends ReactiveCrudRepository<WeatherEntity, Integer> {

    @Query("select w.* from weathers w where w.is_visible=true and w.id in(select s.weather_id from subscriptions s where s.weather_id = :userId)")
    Flux<WeatherEntity> findAllByUserSubscriptions(Integer userId);
    Mono<WeatherEntity> findByCity(String city);
    Mono<WeatherEntity> findByIdAndIsVisible(Integer id,Boolean isVisible);
    Mono<WeatherEntity> findByIdAndCity(Integer id, String city);
}
