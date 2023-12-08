package dk.kea.project.service;

import dk.kea.project.dto.RecipeRequest;
import dk.kea.project.dto.RecipeResponse;
import dk.kea.project.entity.Offer;
import dk.kea.project.entity.Recipe;
import dk.kea.project.entity.User;
import dk.kea.project.repository.OfferRepository;
import dk.kea.project.repository.RecipeRepository;
import dk.kea.project.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Service
public class RecipeService {
    RecipeRepository recipeRepository;
    OfferRepository offerRepository;
    UserRepository userRepository;

    public RecipeService(RecipeRepository recipeRepository, OfferRepository offerRepository, UserRepository userRepository) {
         this.recipeRepository = recipeRepository;
         this.offerRepository = offerRepository;
         this.userRepository = userRepository;
    }

    /**
     * saves a recipe to the repository
     * @param recipeRequest
     * @param principal
     */

    public ResponseStatusException saveRecipe(RecipeRequest recipeRequest, Principal principal) {
        Recipe recipe = new Recipe();
        recipe.setRecipeBody(recipeRequest.getRecipeBody());
        recipe.setRecipeTitle(recipeRequest.getRecipeTitle());
        List<Offer> offers = recipeRequest.getOffers().stream().map(offer -> offerRepository.findAllById(offer.getId())).toList();
        recipe.setOffers(offers);
        recipe.setUser(userRepository.findUserByUsername(principal.getName()));
        recipeRepository.save(recipe);
        return new ResponseStatusException(HttpStatus.ACCEPTED, "Recipe saved");
    }
    public ResponseStatusException saveRecipeAdmin(RecipeRequest recipeRequest, Principal principal ) {
        Recipe recipe = new Recipe();
        recipe.setRecipeBody(recipeRequest.getRecipeBody());
        recipe.setRecipeTitle(recipeRequest.getRecipeTitle());
        List<Offer> offers = recipeRequest.getOffers();
        recipe.setOffers(offers);
        recipe.setUser(userRepository.findUserByUsername(principal.getName()));
        recipeRepository.save(recipe);
        return new ResponseStatusException(HttpStatus.ACCEPTED, "Recipe saved");
    }
    /**
     * Retrieves all recipes from the repository and converts them into a list of {@code RecipeResponse}.
     *
     * @return A list of {@code RecipeResponse} representing all the recipes.
     */
    public List<RecipeResponse> getAllRecipes(Principal principal) {
        User user = userRepository.findUserByUsername(principal.getName());
        List<Recipe> recipes = recipeRepository.findAllByUser(user);
        List<RecipeResponse> response = recipes.stream().map(RecipeResponse::new).toList();

        return response;
    }


    /**
     * Updates a recipe based on the information provided in the {@code RecipeRequest}.
     *
     * @param recipeRequest The {@code RecipeRequest} containing information about the recipe to be updated.
     * @return A {@code RecipeResponse} representing the result of the update operation.
     *         If the recipe with the given ID is not found, returns {@code null}.
     */
    public RecipeResponse updateRecipe(RecipeRequest recipeRequest) {
        Optional<Recipe> existingRecipeOptional = recipeRepository.findById(recipeRequest.getId());

        if (existingRecipeOptional.isPresent()) {
            Recipe existingRecipe = existingRecipeOptional.get();

            if (recipeRequest.getRecipeTitle() != null) {
                existingRecipe.setRecipeTitle(recipeRequest.getRecipeTitle());
            }

            if (recipeRequest.getRecipeBody() != null) {
                existingRecipe.setRecipeBody(recipeRequest.getRecipeBody());
            }
            
            Recipe updatedRecipe = recipeRepository.save(existingRecipe);

            return new RecipeResponse(updatedRecipe);
        } else {
            // Handle the case where the recipe with the given ID is not found
            // You might throw an exception, return an error response, or handle it as appropriate
            return null;
        }
    }


    /**
     * Deletes a recipe based on the information provided in the {@code RecipeRequest}.
     *
     * @param recipeRequest The {@code RecipeRequest} containing information about the recipe to be deleted.
     * @return A {@code RecipeResponse} representing the result of the delete operation.
     *         If the recipe with the given ID is not found, returns {@code null}.
     */
    public RecipeResponse deleteRecipe(RecipeRequest recipeRequest) {
        Optional<Recipe> existingRecipeOptional = recipeRepository.findById(recipeRequest.getId());

        if (existingRecipeOptional.isPresent()) {
            Recipe existingRecipe = existingRecipeOptional.get();

            // Delete the recipe from the repository
            recipeRepository.deleteById(recipeRequest.getId());

            // Return a response indicating successful deletion
            return new RecipeResponse(existingRecipe);
        } else {
            // Handle the case where the recipe with the given ID is not found
            // You might throw an exception, return an error response, or handle it as appropriate
            return null;
        }
    }
}
