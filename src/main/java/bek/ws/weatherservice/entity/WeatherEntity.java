package bek.ws.weatherservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "weathers")
@Getter
@Setter
public class WeatherEntity {
    @Id
    private Integer id;
    private String city;
    private Float temperature;
    private String description;
    private LocalDateTime date;
    private Boolean isVisible;
}
