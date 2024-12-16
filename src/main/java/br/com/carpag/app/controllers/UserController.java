package br.com.carpag.app.controllers;

import br.com.carpag.app.dtos.request.UserRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {


    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDto requestDto){
        return null;
    }
}
