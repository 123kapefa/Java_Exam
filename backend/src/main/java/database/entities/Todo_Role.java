package database.entities;

import javax.persistence.*;
import models.Role;

@Entity
@Table(name = "roles")
public class Todo_Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Role name;

    public Todo_Role() { }

    public Todo_Role(Role role) {
        this.name = name;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Role getName() { return name; }
    public void setName(Role name) { this.name = name; }
}
