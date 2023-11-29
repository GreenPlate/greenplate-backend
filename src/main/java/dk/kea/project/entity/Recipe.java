package dk.kea.project.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @JoinColumn(name = "user_id")
    private User user;

    public Recipe(String recipeTitle, String recipeBody, String recipeIngredients, User user){
        this.recipeTitle = recipeTitle;
        this.recipeBody = recipeBody;
        this.recipeIngredients = recipeIngredients;
        this.user = user;
    }
}
