package br.com.carpag.app.models;

import br.com.carpag.app.dtos.request.UserRequestDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "users")
public class User implements Serializable {

    @Id
    private String id;
    private String name;
    private String email;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedBy
    private Instant updatedAt;

    public static User parseToEntity(UserRequestDto userRequestDto){
        return User.builder().name(userRequestDto.name()).email(userRequestDto.email()).build();
    }
}
