package br.com.carpag.app.factories;

import br.com.carpag.app.dtos.request.UserRequestDto;

public class UserFactory {

    private UserFactory(){}

    public static UserRequestDto makeUserRequestDto(){
        return new UserRequestDto("any_name", "any_mail@mail.com");
    }

}
