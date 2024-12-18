package br.com.carpag.app.controllers;

import br.com.carpag.app.dtos.request.UserRequestDto;
import br.com.carpag.app.dtos.response.UserResponseDto;
import br.com.carpag.app.factories.UserFactory;
import br.com.carpag.app.repositories.UserRepository;
import br.com.carpag.app.services.exceptions.ResourceAlreadyExistsException;
import br.com.carpag.app.services.users.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperties;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.Instant;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    private static final String ROUTE_NAME = "/users";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private UserRequestDto userRequestDto;
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    void setupValues(){
        this.userRequestDto = UserFactory.makeUserRequestDto();
    }

    @BeforeEach
    void setup(){
        setupValues();
    }

    @DisplayName("POST - Should returns 400 is no name is provided")
    @Test
    void shouldReturnsBadRequestWhenNoNameIsProvided() throws Exception{
        UserRequestDto requestDto = new UserRequestDto(null, "anymail@mail.com");
        String jsonBody = mapDataToString(requestDto);
        ResultActions resultActions = mockMvc.perform(post(ROUTE_NAME)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        resultActions.andExpect(status().isBadRequest());
    }

    @DisplayName("POST - Should return 400 if no email is provided")
    @Test
    void shouldReturnsBadRequestWhenNoEmailIsProvided() throws Exception{
        UserRequestDto requestDto = new UserRequestDto("any_name", null);
        String jsonBody = mapDataToString(requestDto);
        ResultActions resultActions = mockMvc.perform(post(ROUTE_NAME)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isBadRequest());
    }

    @DisplayName("POST - Should returns 400 if invalid email is provided")
    @Test
    void shouldReturnsBadRequestWhenInvalidEmailIsProvided() throws Exception{
      UserRequestDto requestDto = new UserRequestDto("any_name", "invalid_email");
      String jsonBody = mapDataToString(requestDto);
      ResultActions resultActions = mockMvc.
                perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
                        .accept(MediaType.APPLICATION_JSON)
                );
      resultActions.andExpect(status().isBadRequest());
    }

    @DisplayName("GET - Should returns 200 when loadUsers sucessed")
    @Test
    void shouldReturnAListOfUsersOnSuccess() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk());
    }




    public  String mapDataToString(Object object) throws Exception{
        return objectMapper.writeValueAsString(object);
    }

}