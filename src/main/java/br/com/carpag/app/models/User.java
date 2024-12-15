package br.com.carpag.app.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Document(collection = "users")
public class User implements Serializable {

    @Id
    private UUID id;
    private String name;
    private String email;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedBy
    private Instant updatedAt;
}
