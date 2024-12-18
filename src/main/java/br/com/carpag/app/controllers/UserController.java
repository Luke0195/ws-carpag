package br.com.carpag.app.controllers;

import br.com.carpag.app.dtos.request.UserRequestDto;
import br.com.carpag.app.dtos.response.UserResponseDto;
import br.com.carpag.app.services.users.UserService;
import br.com.carpag.app.utils.HttpUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto requestDto){
        UserResponseDto userResponseDto = userService.addUser(requestDto);
        return HttpUtil.created(userResponseDto, userResponseDto.id());
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> loadUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.loadUsers());
    }
}
