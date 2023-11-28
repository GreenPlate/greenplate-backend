package dk.kea.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dk.kea.project.entity.Recipe;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeResponse {
    
    private int id;
    private String recipeTitle;
    private String recipeBody;
    private String recipeIngredients;

    public RecipeResponse(Recipe recipe) {
        this.id = recipe.getId();
        this.recipeTitle = recipe.getRecipeTitle();
        this.recipeBody = recipe.getRecipeBody();
        this.recipeIngredients = recipe.getRecipeIngredients();
    }
}
