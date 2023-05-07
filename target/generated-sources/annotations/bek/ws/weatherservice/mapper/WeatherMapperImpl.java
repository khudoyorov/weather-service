package bek.ws.weatherservice.mapper;

import bek.ws.weatherservice.dto.WeatherDto;
import bek.ws.weatherservice.entity.WeatherEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-07T12:37:19+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class WeatherMapperImpl implements WeatherMapper {

    @Override
    public WeatherDto toDto(WeatherEntity entity) {
        if ( entity == null ) {
            return null;
        }

        WeatherDto weatherDto = new WeatherDto();

        weatherDto.setId( entity.getId() );
        weatherDto.setCity( entity.getCity() );
        weatherDto.setTemperature( entity.getTemperature() );
        weatherDto.setDescription( entity.getDescription() );
        weatherDto.setDate( entity.getDate() );
        weatherDto.setIsVisible( entity.getIsVisible() );

        return weatherDto;
    }

    @Override
    public WeatherEntity toEntity(WeatherDto dto) {
        if ( dto == null ) {
            return null;
        }

        WeatherEntity weatherEntity = new WeatherEntity();

        weatherEntity.setId( dto.getId() );
        weatherEntity.setCity( dto.getCity() );
        weatherEntity.setTemperature( dto.getTemperature() );
        weatherEntity.setDescription( dto.getDescription() );
        weatherEntity.setDate( dto.getDate() );
        weatherEntity.setIsVisible( dto.getIsVisible() );

        return weatherEntity;
    }
}
