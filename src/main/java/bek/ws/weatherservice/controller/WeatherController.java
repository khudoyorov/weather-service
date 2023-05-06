package bek.ws.weatherservice.controller;

import bek.ws.weatherservice.dto.WeatherDto;
import bek.ws.weatherservice.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("cities")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;
    @GetMapping
    public Flux<WeatherDto> getSubscriptions(){
        return weatherService.getSubscriptions();
    }
    @GetMapping("cities-list")
    public Flux<String> getCities(){
        return weatherService.getCitiesList();
    }

    @PostMapping("subscribe")
    public Mono<WeatherDto> subscribeToCity(@RequestParam Integer id){
        return weatherService.subscribeToCity(id);
    }
    @PreAuthorize("hasAuthority('admin')")
    @PatchMapping
    public Mono<WeatherDto> updateCityWeather(@RequestBody WeatherDto weatherDto){
        return weatherService.updateWeather(weatherDto);
    }
    @PreAuthorize("hasRole('admin')")
    @PostMapping("add-city")
    public Mono<WeatherDto> addCity(@RequestBody WeatherDto weatherDto){
        return weatherService.addCity(weatherDto);
    }
    @GetMapping("by-city")
    public Mono<WeatherDto> getByCity(@RequestParam String city){
        return weatherService.getByCity(city);
    }

}
