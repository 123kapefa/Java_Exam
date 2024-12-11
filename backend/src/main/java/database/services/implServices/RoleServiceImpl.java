package database.services.implServices;

import database.entities.Todo_Role;
import database.repositories.TodoRoleRepository;
import database.services.RoleService;
import javax.transaction.Transactional;
import models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Repository
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private TodoRoleRepository roleRepository;

    @Override public Optional<Todo_Role> findByName(Role name) { return roleRepository.findByName(name); }
}
