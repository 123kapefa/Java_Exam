package database.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import models.Role;

import java.util.List;


@Entity
@Table(name = "users", schema = "public")
public class Todo_User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinTable(name = "roles",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Todo_Role role;

    @OneToMany(mappedBy = "emailFromUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo_Email> sentEmails;

    @OneToMany(mappedBy = "emailToUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo_Email> receivedEmails;

    public Todo_User() { }
    public Todo_User(String username, String login, String email, String password) {
        this.username = username;
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public Long getId() { return id; }
    public Todo_Role getRole() { return role; }
    public String getUsername() { return username; }
    public String getLogin() { return login; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    public Long setId(Long id) { return this.id = id; }
    public Todo_Role setRole(Todo_Role role) { return this.role = role; }
    public String setUsername(String username) { return this.username = username; }
    public String setLogin(String login) { return this.login = login; }
    public String setEmail(String email) { return this.email = email; }
    public String setPassword(String password) { return this.password = password; }
}