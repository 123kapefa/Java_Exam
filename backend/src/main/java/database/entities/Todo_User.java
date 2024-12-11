package database.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", schema = "public")
public class Todo_User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable(name = "roles",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Todo_Role> role = new HashSet<>();

    @OneToMany(mappedBy = "emailFromUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo_Email> sentEmails;

    @OneToMany(mappedBy = "emailToUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo_Email> receivedEmails;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


    public Todo_User() { }
    public Todo_User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public Long getId() { return id; }
    public Set<Todo_Role> getRole() { return role; }
    public String getLogin() { return login; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    public Long setId(Long id) { return this.id = id; }
    public String setLogin(String login) { return this.login = login; }
    public String setEmail(String email) { return this.email = email; }
    public String setPassword(String password) { return this.password = password; }
}