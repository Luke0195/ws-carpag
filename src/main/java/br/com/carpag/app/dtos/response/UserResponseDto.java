package br.com.carpag.app.dtos.response;

import java.util.UUID;

public record UserResponseDto(UUID id, String name, String email, String ) {
}
