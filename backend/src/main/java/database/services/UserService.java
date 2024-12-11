package database.services;

import database.entities.Todo_User;
import java.util.Optional;

public interface UserService {
    Todo_User save (Todo_User user);

    Iterable<Todo_User>  findAll();
    Optional<Todo_User> findById(Long userId);
    Optional<Todo_User> findByLogin(String login);
    Optional<Todo_User> findByEmail(String email);

    Boolean existsByEmail(String email);
    Boolean existsByLogin(String login);
}
