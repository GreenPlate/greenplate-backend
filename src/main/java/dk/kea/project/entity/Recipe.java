package dk.kea.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@SuppressWarnings("ALL")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipe {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String recipeTitle;
    @Column(length = 3000)
    private String recipeBody;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    List<Offer> offers;

    public Recipe(String recipeTitle, String recipeBody, List<Offer> offers){
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
