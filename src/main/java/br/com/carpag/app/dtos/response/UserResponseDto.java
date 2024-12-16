package br.com.carpag.app.dtos.response;

import br.com.carpag.app.models.User;

import java.time.Instant;
import java.util.UUID;

public record UserResponseDto(UUID id, String name, String email, Instant createdAt, Instant updatedAt) {
    @Override
    public UUID id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public Instant createdAt() {
        return createdAt;
    }

    @Override
    public Instant updatedAt() {
        return updatedAt;
    }

    public static UserResponseDto toDto(User user){
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt(), user
                .getUpdatedAt());
    }
}
