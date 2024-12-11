package database.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
@Entity
@Table(name = "emails")
public class Todo_Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email_to", nullable = false)
    private int emailTo;

    @Column(name = "email_from", nullable = false)
    private int emailFrom;

    @ManyToOne
    @JoinColumn(name = "email_to", referencedColumnName = "id", insertable = false, updatable = false)
    private Todo_User emailToUser;

    @ManyToOne
    @JoinColumn(name = "email_from", referencedColumnName = "id", insertable = false, updatable = false)
    private Todo_User emailFromUser;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String context;
}