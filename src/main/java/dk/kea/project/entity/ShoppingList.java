package dk.kea.project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShoppingList {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    private List<Offer> offers;

    @CreationTimestamp
    LocalDateTime createdAt;

    public ShoppingList(User user, List<Offer> offers, LocalDateTime now) {
        this.user = user;
        this.offers = offers;
        this.createdAt = now;
    }
}
