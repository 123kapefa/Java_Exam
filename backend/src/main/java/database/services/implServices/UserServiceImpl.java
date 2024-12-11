//package database.services.implServices;
//
//import database.entities.Todo_User;
//import database.repositories.TodoUserRepository;
//import database.services.UserService;
//import javax.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@Repository
//@Transactional
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private TodoUserRepository userRepository;
//
//    @Override public Todo_User save(Todo_User user) { return (Todo_User) userRepository.save(user); }
//
//    @Override public Iterable<Todo_User> findAll() { return userRepository.findAll(); }
//    @Override public Optional<Todo_User> findById(Long userId) { return userRepository.findById(userId); }
//    @Override public Optional<Todo_User> findByLogin(String login) { return userRepository.findByLogin(login); }
//    @Override public Optional<Todo_User> findByEmail(String email) { return userRepository.findByEmail(email); }
//
//
//    @Override public Boolean existsByEmail(String email) { return userRepository.existsByEmail(email); }
//    @Override public Boolean existsByLogin(String login) { return userRepository.existsByLogin(login); }
//
//}


 package database.services.implServices;

import database.entities.Todo_User;
import database.repositories.TodoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private TodoUserRepository userRepository;

    public UserServiceImpl(TodoUserRepository userRepository) {
        this.userRepository=userRepository;
    }

    public Todo_User save(Todo_User user) { return (Todo_User) userRepository.save(user); }

    public Iterable<Todo_User> findAll() { return userRepository.findAll(); }
    public Optional<Todo_User> findById(Long userId) { return userRepository.findById(userId); }
    public Optional<Todo_User> findByLogin(String login) { return userRepository.findByLogin(login); }
    public Optional<Todo_User> findByEmail(String email) { return userRepository.findByEmail(email); }

    public Boolean existsByEmail(String email) { return userRepository.existsByEmail(email); }
    public Boolean existsByLogin(String login) { return userRepository.existsByLogin(login); }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Todo_User> user = userRepository.findByUsername(username);
        if (user.get() == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        return buildUserDetails(user.get());
    }

    public UserDetails loadUserByLogin(String login) throws UsernameNotFoundException {
        Optional<Todo_User> user = userRepository.findByLogin(login); // поиск по login
        if (user.get() == null) {
            throw new UsernameNotFoundException("User not found with login: " + login);
        }
        return buildUserDetails(user.get());
    }

    private UserDetails buildUserDetails(Todo_User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}