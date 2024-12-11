package database.repositories;

import database.entities.Todo_Role;
import models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRoleRepository extends JpaRepository<Todo_Role, Long> {
    Optional<Todo_Role> findByName (Role name);
}
