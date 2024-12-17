package br.com.carpag.app.repositories;

import br.com.carpag.app.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, UUID> {

    @Query(value = "{'email': ?0}")
    Optional<User> findByEmail(String email);
}
