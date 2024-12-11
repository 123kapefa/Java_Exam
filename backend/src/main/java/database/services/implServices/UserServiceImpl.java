package database.services.implServices;

import database.entities.Todo_User;
import database.repositories.TodoUserRepository;
import database.services.UserService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Repository
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private TodoUserRepository userRepository;

    @Override public Todo_User save(Todo_User user) { return (Todo_User) userRepository.save(user); }

    @Override public Iterable<Todo_User> findAll() { return userRepository.findAll(); }
    @Override public Optional<Todo_User> findById(Long userId) { return userRepository.findById(userId); }
    @Override public Optional<Todo_User> findByLogin(String login) { return userRepository.findByLogin(login); }
    @Override public Optional<Todo_User> findByEmail(String email) { return userRepository.findByEmail(email); }


    @Override public Boolean existsByEmail(String email) { return userRepository.existsByEmail(email); }
    @Override public Boolean existsByLogin(String login) { return userRepository.existsByLogin(login); }

}
