package bek.ws.weatherservice.service;

import bek.ws.weatherservice.dto.WeatherDto;
import bek.ws.weatherservice.entity.SubscriptionEntity;
import bek.ws.weatherservice.entity.UserEntity;
import bek.ws.weatherservice.entity.WeatherEntity;
import bek.ws.weatherservice.mapper.WeatherMapper;
import bek.ws.weatherservice.repository.SubscriptionRepository;
import bek.ws.weatherservice.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RequiredArgsConstructor
@Service
public class WeatherService {
    private final WeatherRepository weatherRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final WeatherMapper weatherMapper;

    public Flux<String> getCitiesList() {
        return weatherRepository.findAll()
                .map(WeatherEntity::getCity);
    }

    public Flux<WeatherDto> getSubscriptions() {
        return ReactiveSecurityContextHolder.getContext()
                .flatMapMany(context -> {
                    UserEntity user = (UserEntity) context.getAuthentication().getPrincipal();
                    return weatherRepository.findAllByUserSubscriptions(user.getId())
                            .map(weatherMapper::toDto);
                });
    }


    public Mono<WeatherDto> subscribeToCity(Integer id) {
        return ReactiveSecurityContextHolder.getContext()
                .flatMap(u -> {
                    UserEntity user = (UserEntity) u.getAuthentication().getPrincipal();
                    return weatherRepository.findByIdAndIsVisible(id, true)
                            .map(weatherMapper::toDto)
                            .zipWith(subscriptionRepository.save(SubscriptionEntity.builder().userId(user.getId()).weatherId(id).build()))
                            .map(Tuple2::getT1)
                            .switchIfEmpty(Mono.error(new RuntimeException("City not found")));
                });
    }

    public Mono<WeatherDto> updateWeather(WeatherDto weatherDto) {
        return weatherRepository.findByIdAndCity(weatherDto.getId(), weatherDto.getCity())
                .flatMap(o -> weatherRepository.save(weatherMapper.toEntity(weatherDto)))
                .map(weatherMapper::toDto)
                .switchIfEmpty(Mono.error(new RuntimeException("City does not exists")));

    }

    public Mono<WeatherDto> getByCity(String city) {
        return weatherRepository.findByCity(city)
                .map(weatherMapper::toDto);
    }


    public Mono<WeatherDto> addCity(WeatherDto weatherDto) {
        return weatherRepository.save(weatherMapper.toEntity(weatherDto))
                .map(weatherMapper::toDto);
    }
}
