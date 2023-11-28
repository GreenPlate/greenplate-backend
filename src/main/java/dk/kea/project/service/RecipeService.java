package dk.kea.project.service;

import dk.kea.project.dto.RecipeRequest;
import dk.kea.project.entity.Recipe;
import dk.kea.project.repository.RecipeRepository;
import org.springframework.stereotype.Service;


@Service
public class RecipeService {
    RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public void saveRecipe(RecipeRequest recipeRequest) {
        Recipe recipe = new Recipe();
        recipe.setRecipeIngredients(recipeRequest.getRecipeIngredients());
        recipe.setRecipeBody(recipeRequest.getRecipeBody());
        recipe.setRecipeTitle(recipeRequest.getRecipeTitle());
        recipeRepository.save(recipe);
    }
}
