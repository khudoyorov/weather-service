package bek.ws.weatherservice.controller;

import bek.ws.weatherservice.dto.UserDto;
import bek.ws.weatherservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("sign-up")
    public Mono<UserDto> addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @GetMapping("sign-in")
    public Mono<String> getToken(@RequestParam String username, @RequestParam String password) {
        return userService.getToken(username, password);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PatchMapping("edit")
    public Mono<UserDto> editUser(@RequestBody UserDto userDto){
        return userService.editUser(userDto);
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("get-all")
    public Flux<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

}
