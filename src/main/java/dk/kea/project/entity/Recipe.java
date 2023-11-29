package dk.kea.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @ManyToMany
    List<Offer> offer;

    public Recipe(String recipeTitle, String recipeBody){
        this.recipeTitle = recipeTitle;
        this.recipeBody = recipeBody;
    }
}
