package bek.ws.weatherservice.mapper;

import bek.ws.weatherservice.dto.UserDto;
import bek.ws.weatherservice.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UserMapper implements CommonMapper<UserEntity, UserDto> {
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "createdAt", source = "createdAt", dateFormat = "dd.MM.yyyy HH:mm:ss")
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(dto.getPassword()))")
    public abstract UserEntity toEntity(UserDto dto);

    @Mapping(target = "createdAt", source = "createdAt", dateFormat = "dd.MM.yyyy HH:mm:ss")
    @Mapping(target = "password", expression = "java(null)")
    public abstract UserDto toDto(UserEntity entity);

}
