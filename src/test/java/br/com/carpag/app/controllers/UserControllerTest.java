package br.com.carpag.app.controllers;

import br.com.carpag.app.dtos.request.UserRequestDto;
import br.com.carpag.app.factories.UserFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.result.RequestResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        String jsonBody = objectMapper.writeValueAsString(requestDto);
        ResultActions resultActions = mockMvc.perform(post(ROUTE_NAME)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        resultActions.andExpect(status().isBadRequest());
    }
}