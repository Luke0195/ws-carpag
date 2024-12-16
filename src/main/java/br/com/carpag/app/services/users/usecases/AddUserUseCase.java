package br.com.carpag.app.services.users.usecases;

import br.com.carpag.app.dtos.request.UserRequestDto;
import br.com.carpag.app.dtos.response.UserResponseDto;

public interface AddUserUseCase {

    UserResponseDto addUser(UserRequestDto userRequestDto);
}
