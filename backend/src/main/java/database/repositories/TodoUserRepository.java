package database.repositories;

import database.entities.Todo_User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoUserRepository extends CrudRepository<Todo_User, Long> {
    Optional<Todo_User> findById(Long userId);
    Optional<Todo_User> findByLogin(String login);
    Optional<Todo_User> findByUsername(String username);
    Optional<Todo_User> findByEmail(String email);

    Boolean existsByLogin(String login);
    Boolean existsByEmail(String email);
}
