package br.com.carpag.app.services.users;

import br.com.carpag.app.dtos.request.UserRequestDto;
import br.com.carpag.app.factories.UserFactory;
import br.com.carpag.app.models.User;
import br.com.carpag.app.repositories.UserRepository;
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

    @DisplayName("addUserUseCase should returns an exception when user e-mail already exists")
    @Test
    void addUserUseCaseShouldReturnsAnExceptionWhenUserEmailAlreadyExists(){
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenThrow(RuntimeException.class);
        UserRequestDto userRequestDto = new UserRequestDto("any_name", "valid_email@mail.com");
        Assertions.assertThrows(RuntimeException.class, () -> {
            userService.addUser(userRequestDto);
        });
    }



}