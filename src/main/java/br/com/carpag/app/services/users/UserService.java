package br.com.carpag.app.services.users;

import br.com.carpag.app.dtos.request.UserRequestDto;
import br.com.carpag.app.dtos.response.UserResponseDto;
import br.com.carpag.app.models.User;
import br.com.carpag.app.repositories.UserRepository;
import br.com.carpag.app.services.exceptions.ResourceAlreadyExistsException;
import br.com.carpag.app.services.users.usecases.AddUserUseCase;
import br.com.carpag.app.services.users.usecases.LoadUsers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements AddUserUseCase, LoadUsers {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
      Optional<User> findUserByEmail = userRepository.findByEmail(userRequestDto.email());
      if(findUserByEmail.isPresent()) throw new ResourceAlreadyExistsException("This email is already taken");
      User user = User.parseToEntity(userRequestDto);
      user = userRepository.save(user);
      return UserResponseDto.toDto(user);
    }


    @Override
    public List<UserResponseDto> loadUsers() {
        return userRepository.findAll().stream().map(UserResponseDto::toDto).toList();
    }
}
