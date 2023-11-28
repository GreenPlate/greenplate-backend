package dk.kea.project.dto;

import lombok.*;

/**
 * Represents a request object for managing recipes.
 * This class is used for creating, updating, and deleting recipes.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeRequest {

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
     * The ingredients used in the recipe.
     */
    private String recipeIngredients;

    /**
     * Creates a new {@code RecipeRequest} with the specified title, body, and ingredients.
     *
     * @param recipeTitle       The title of the recipe.
     * @param recipeBody        The body or content of the recipe.
     * @param recipeIngredients The ingredients used in the recipe.
     */
    public RecipeRequest(String recipeTitle, String recipeBody, String recipeIngredients) {
        this.recipeTitle = recipeTitle;
        this.recipeBody = recipeBody;
        this.recipeIngredients = recipeIngredients;
    }
}
