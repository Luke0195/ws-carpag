package br.com.carpag.app.services.users.usecases;

import br.com.carpag.app.dtos.response.UserResponseDto;

import java.util.List;

public interface LoadUsers {
    List<UserResponseDto> loadUsers();
}
