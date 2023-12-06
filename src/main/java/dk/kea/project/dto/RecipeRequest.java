package dk.kea.project.dto;

import dk.kea.project.entity.Offer;
import lombok.*;

import java.util.List;

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
    private List<Offer> offers;

    /**
     * Creates a new {@code RecipeRequest} with the specified title, body, and ingredients.
     *
     * @param recipeTitle       The title of the recipe.
     * @param recipeBody        The body or content of the recipe.
     * @param offers            The list of offers used in the recipe.
     */
    public RecipeRequest(String recipeTitle, String recipeBody, List<Offer> offers) {
        this.recipeTitle = recipeTitle;
        this.recipeBody = recipeBody;
        this.offers = offers;
    }

    public RecipeRequest(String recipeTitle, String recipeBody) {
        this.recipeTitle = recipeTitle;
        this.recipeBody = recipeBody;
    }
}
