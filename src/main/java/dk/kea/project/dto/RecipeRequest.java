package dk.kea.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeRequest {
    private String recipeTitle;
    private String recipeBody;
    private String recipeIngredients;
}
