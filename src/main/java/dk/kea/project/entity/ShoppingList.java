package dk.kea.project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
/**
 * Entity class representing a shopping list.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShoppingList {
    /**
     * Unique identifier for the shopping list.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    /**
     * User associated with the shopping list.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    /**
     * List of offers associated with the shopping list.
     */
    @ManyToMany
    private List<Offer> offers;
    /**
     * Date and time of creation of the shopping list.
     */
    @CreationTimestamp
    LocalDateTime createdAt;
    /**
     * Constructor for the ShoppingList class.
     *
     * @param user   The user associated with the shopping list.
     * @param offers The list of offers included in the shopping list.
     * @param now    The timestamp indicating when the shopping list is created.
     */
    public ShoppingList(User user, List<Offer> offers, LocalDateTime now) {
        this.user = user;
        this.offers = offers;
        this.createdAt = now;
    }
}
