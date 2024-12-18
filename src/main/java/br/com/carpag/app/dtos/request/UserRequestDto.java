package br.com.carpag.app.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserRequestDto(
        @NotEmpty(message = "The field name must be required")
        String name,
        @NotEmpty(message = "The field email must be required")
        @Email(message = "Please provided a valid e-mail")
        String email) {
}
