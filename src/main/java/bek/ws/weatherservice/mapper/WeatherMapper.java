package bek.ws.weatherservice.mapper;

import bek.ws.weatherservice.dto.WeatherDto;
import bek.ws.weatherservice.entity.WeatherEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherMapper extends CommonMapper<WeatherEntity, WeatherDto> {
}
