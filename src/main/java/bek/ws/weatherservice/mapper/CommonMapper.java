package bek.ws.weatherservice.mapper;

public interface CommonMapper<E,D> {
    D toDto(E entity);
    E toEntity(D dto);
}
