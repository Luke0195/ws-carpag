package br.com.carpag.app.services.users;

import br.com.carpag.app.dtos.request.UserRequestDto;
import br.com.carpag.app.dtos.response.UserResponseDto;
import br.com.carpag.app.models.User;
import br.com.carpag.app.repositories.UserRepository;
import br.com.carpag.app.services.users.usecases.AddUserUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements AddUserUseCase {

    private final UserRepository userRepository;


    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
      Optional<User> findUserByEmail = userRepository.findByEmail(userRequestDto.email());
      if(findUserByEmail.isPresent()) throw new RuntimeException("This email is already taken");
      return null;

    }

}
