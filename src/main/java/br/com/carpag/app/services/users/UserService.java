package br.com.carpag.app.services.users;

import br.com.carpag.app.dtos.request.UserRequestDto;
import br.com.carpag.app.dtos.response.UserResponseDto;
import br.com.carpag.app.repositories.UserRepository;
import br.com.carpag.app.services.users.usecases.AddUserUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements AddUserUseCase {

    private final UserRepository userRepository;


    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        return null;
    }
}
