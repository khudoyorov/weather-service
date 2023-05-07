package bek.ws.weatherservice.mapper;

import bek.ws.weatherservice.dto.UserDto;
import bek.ws.weatherservice.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-07T12:37:18+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public UserEntity toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setCreatedAt( dto.getCreatedAt() );
        userEntity.setId( dto.getId() );
        userEntity.setName( dto.getName() );
        userEntity.setPhoneNumber( dto.getPhoneNumber() );
        userEntity.setUsername( dto.getUsername() );
        userEntity.setRole( dto.getRole() );

        userEntity.setPassword( passwordEncoder.encode(dto.getPassword()) );

        return userEntity;
    }

    @Override
    public UserDto toDto(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setCreatedAt( entity.getCreatedAt() );
        userDto.setId( entity.getId() );
        userDto.setName( entity.getName() );
        userDto.setPhoneNumber( entity.getPhoneNumber() );
        userDto.setUsername( entity.getUsername() );
        userDto.setRole( entity.getRole() );

        userDto.setPassword( null );

        return userDto;
    }
}
