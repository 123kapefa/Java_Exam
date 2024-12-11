package database.services;

import database.entities.Todo_Role;
import models.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Todo_Role> findByName (Role name);
}
