package bek.ws.weatherservice.service;

import bek.ws.weatherservice.dto.UserDto;
import bek.ws.weatherservice.mapper.UserMapper;
import bek.ws.weatherservice.repository.UserRepository;
import bek.ws.weatherservice.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements ReactiveUserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public Mono<UserDto> addUser(UserDto userDto) {
        userDto.setRole("user");
        return userRepository.findByUsername(userDto.getUsername())
                .filter(user -> {
                    throw new RuntimeException("User with username " + user.getUsername() + " is already exists");
                })
                .switchIfEmpty(userRepository.save(userMapper.toEntity(userDto)))
                .map(userMapper::toDto);
    }

    public Mono<String> getToken(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(jwtService::generateToken)
                .switchIfEmpty(Mono.just("Password or username not Correct"));
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userEntity -> userEntity);

    }

    public Mono<UserDto> editUser(UserDto userDto) {
        return userRepository.save(userMapper.toEntity(userDto))
                .map(userMapper::toDto);

    }

    public Flux<UserDto> getAllUsers() {
        return userRepository.findAll()
                .map(userMapper::toDto);
    }
}
