package dk.kea.project.service;

import dk.kea.project.dto.RecipeRequest;
import dk.kea.project.dto.RecipeResponse;
import dk.kea.project.entity.Recipe;
import dk.kea.project.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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
    
    public List<RecipeResponse> getAllRecipes(){
        List<Recipe> recipes = recipeRepository.findAll();
        List<RecipeResponse> response = recipes.stream().map((recipe -> new RecipeResponse(recipe))).toList();
        
        return response;
    }
    
    public RecipeResponse updateRecipe(RecipeRequest recipeRequest){
        Optional<Recipe> existingRecipeOptional = recipeRepository.findById(recipeRequest.getId());
        if (existingRecipeOptional.isPresent()) {
            Recipe existingRecipe = existingRecipeOptional.get();

            if (recipeRequest.getRecipeTitle() != null) {
                existingRecipe.setRecipeTitle(recipeRequest.getRecipeTitle());
            }

            if (recipeRequest.getRecipeBody() != null) {
                existingRecipe.setRecipeBody(recipeRequest.getRecipeTitle());
            }

            if (recipeRequest.getRecipeIngredients() != null) {
                existingRecipe.setRecipeBody(recipeRequest.getRecipeIngredients());
            }
            
            Recipe updatedRecipe = recipeRepository.save(existingRecipe);
            
            return new RecipeResponse(updatedRecipe);

        } else {
            // Handle the case where the recipe with the given ID is not found
            // You might throw an exception, return an error response, or handle it as appropriate
            return null;
        }
    }
}
