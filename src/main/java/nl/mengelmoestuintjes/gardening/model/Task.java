package nl.mengelmoestuintjes.gardening.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties("tasks")
    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "username")
    private User owner;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskType type;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private boolean done;

    private LocalDate created;
    private LocalDate deadline;

    public void setCreated() {
        this.created = LocalDate.now();
    }
    public String getOwner() {
        return owner.getUsername();
    }

    public int daysTillDeadline() {
        /*
         * It returns 0 if both the dates are equal.
         * It returns positive value if “created” is greater than the deadline.
         * It returns negative value if “created” is less than the deadline.
         */
        return (created.compareTo(deadline));
    }
}
