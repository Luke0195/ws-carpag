package br.com.carpag.app.services.users;

import br.com.carpag.app.dtos.request.UserRequestDto;
import br.com.carpag.app.dtos.response.UserResponseDto;
import br.com.carpag.app.factories.UserFactory;
import br.com.carpag.app.models.User;
import br.com.carpag.app.repositories.UserRepository;
import br.com.carpag.app.services.exceptions.ResourceAlreadyExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("dev")
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private UserRequestDto userRequestDto;


    void setupValues(){
        this.userRequestDto = UserFactory.makeUserRequestDto();
    }

    @BeforeEach
    void setup(){
        setupValues();

    }

    @DisplayName("addUserUseCase should returns an ResourceAlreadyExistsException when user e-mail already exists")
    @Test
    void addUserUseCaseShouldReturnsAnExceptionWhenUserEmailAlreadyExists(){
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenThrow(ResourceAlreadyExistsException.class);
        Assertions.assertThrows(ResourceAlreadyExistsException.class, () -> {
            userService.addUser(userRequestDto);
        });
    }

    @DisplayName("addUser should returns an user when valid data is provided")
    @Test
    void addUserUseCaseShouldReturnsAnUserWhenValidDataIsProvided(){
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(new User("any_id", "any_name", "any_email@mail.com", Instant.now(), Instant.now()));
        UserResponseDto userResponseDto = userService.addUser(userRequestDto);
        Assertions.assertNotNull(userResponseDto.id());
        Assertions.assertEquals("any_name", userResponseDto.name());
        Assertions.assertEquals("any_email@mail.com", userResponseDto.email());
    }


    @DisplayName("addUser should throws exception when addUser throws")
    @Test
    void addUserUseCaseShouldThrowsExceptionWhenSaveThrows(){
        Mockito.when(userRepository.save(Mockito.any())).thenThrow(RuntimeException.class);
        Assertions.assertThrows(RuntimeException.class, () -> {
           userService.addUser(userRequestDto);
        });
    }

    @DisplayName("loadUsers should returns a list of users")
    @Test
    void loadShouldReturnsAListOfUsers(){
        Mockito.when(userRepository.findAll()).thenReturn(List.of(User.parseToEntity(UserFactory.makeUserRequestDto())));
        List<User> users = userRepository.findAll();
        Assertions.assertEquals(1, users.size());
    }

}