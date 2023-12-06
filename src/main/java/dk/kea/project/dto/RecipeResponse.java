package dk.kea.project.dto;

import dk.kea.project.entity.Recipe;
import lombok.*;

/**
 * Represents a response object for displaying recipe information.
 * This class is used to present recipe details in a user-friendly format.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeResponse {

    /**
     * The unique identifier for the recipe.
     */
    private int id;

    /**
     * The title of the recipe.
     */
    private String recipeTitle;

    /**
     * The body or content of the recipe.
     */
    private String recipeBody;
    
    /**
     * Creates a new {@code RecipeResponse} based on the provided {@code Recipe} entity.
     *
     * @param recipe The {@code Recipe} entity from which to extract data.
     */
    public RecipeResponse(Recipe recipe) {
        this.id = recipe.getId();
        this.recipeTitle = recipe.getRecipeTitle();
        this.recipeBody = recipe.getRecipeBody();
    }
}
