package dk.kea.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Entity class representing a recipe.
 * <p>
 * This class is mapped to the database table and is used to store information
 * about recipes, including the title, body, associated user, and offers related to the recipe.
 * </p>
 *
 *
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipe {

    /**
     * The unique identifier for the recipe.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    /**
     * The title of the recipe.
     */
    private String recipeTitle;

    /**
     * The body or content of the recipe.
     */
    @Column(length = 3000)
    private String recipeBody;

    /**
     * The user who created the recipe.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The list of offers associated with the recipe.
     */
    @ManyToMany
    List<Offer> offers;

    /**
     * Constructs a {@code Recipe} object with the specified title, body, and associated offers.
     *
     * @param recipeTitle The title of the recipe.
     * @param recipeBody  The body or content of the recipe.
     * @param offers      The list of offers associated with the recipe.
     */
    public Recipe(String recipeTitle, String recipeBody, List<Offer> offers) {
        this.recipeTitle = recipeTitle;
        this.recipeBody = recipeBody;
        this.offers = offers;
    }

    public Recipe(String recipeTitle, String recipeBody, User user, List<Offer> offers) {
        this.recipeTitle = recipeTitle;
        this.recipeBody = recipeBody;
        this.user = user;
        this.offers = offers;
    }
}
