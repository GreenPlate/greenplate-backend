package dk.kea.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeRequest {

    private int id;
    private String recipeTitle;
    private String recipeBody;
    private String recipeIngredients;

    public RecipeRequest(String recipeTitle, String recipeBody, String recipeIngredients) {
        this.recipeTitle = recipeTitle;
        this.recipeBody = recipeBody;
        this.recipeIngredients = recipeIngredients;
    }
}
