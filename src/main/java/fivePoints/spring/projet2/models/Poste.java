package fivePoints.spring.projet2.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "posts")
@NoArgsConstructor
@RequiredArgsConstructor
public class Poste implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int id;

    @NonNull
    @Column(name = "title")
    private String title;
    @NonNull
    @Column(name = "description")
    private String description;
    @NonNull
    @Column(name = "published")
    private boolean published;

    // OneToMany Relations
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Setter(value = AccessLevel.NONE)
    @Basic(optional = false)
    @CreationTimestamp
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Setter(value = AccessLevel.NONE)
    @UpdateTimestamp
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();
}
