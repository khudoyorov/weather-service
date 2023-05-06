package bek.ws.weatherservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WeatherDto {
    private Integer id;
    private String city;
    private Float temperature;
    private String description;
    @JsonFormat(pattern = "dd.MM.yyyy HH.mm.ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime date;
    private Boolean isVisible;
}
