package dk.kea.project.api;

import dk.kea.project.dto.ChatRecipeResponse;
import dk.kea.project.dto.MyRecipe;
import dk.kea.project.dto.RecipeRequest;
import dk.kea.project.dto.RecipeResponse;
import dk.kea.project.service.OpenAIService;
import dk.kea.project.service.RecipeService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.security.Principal;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * REST controller handling recipe-related endpoints.
 * <p>
 * This controller provides endpoints for making recipe requests, saving recipes,
 * and accessing recipe-related functionalities. It includes rate-limiting to prevent abuse.
 * </p>
 *
 *
 */
@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    RecipeService recipeService;
    // Rate limiting parameters
    private final int BUCKET_CAPACITY = 3;
    private final int REFILL_AMOUNT = 3;
    private final int REFILL_TIME = 1440;

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    // Create a new rate limiting bucket with specified parameters
    private Bucket createNewBucket() {
        Bandwidth limit = Bandwidth.classic(BUCKET_CAPACITY, Refill.greedy(REFILL_AMOUNT, Duration.ofMinutes(REFILL_TIME)));
        return Bucket.builder().addLimit(limit).build();
    }

    // Get or create a rate limiting bucket for a client based on their IP address
    private Bucket getBucket(String key) {
        return buckets.computeIfAbsent(key, k -> createNewBucket());
    }

    private OpenAIService openAIService;
    final static String SYSTEM_MESSAGE = "Lav kun 1 opskrift på få udvalgte ingredienser. Dit svar skal være komplet og under 800 tokens."
            +"Overskrift skal være <h3> med id: recipe-heading, ingredients skal være <ul> og fremgangsmåde skal være <ol>. begge i hver deres <div> med overskrifter i <strong>"
            +"Derudover skal hvert element i fremgangsmåden markeres med <strong> også.";

    public RecipeController(OpenAIService openAIService, RecipeService recipeService){
        this.openAIService = openAIService;
        this.recipeService = recipeService;
    }
    /**
     * Makes a recipe request based on the provided ingredients.
     *
     * @param ingredients The ingredients for the recipe request.
     * @param request     The HTTP servlet request object.
     * @return A {@code MyRecipe} representing the result of the recipe request.
     * @throws ResponseStatusException If there are too many requests from the same client.
     */
    @PostMapping()
    public MyRecipe makeRequest(@RequestBody String ingredients, HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        Bucket bucket = getBucket(ip);

        if (!bucket.tryConsume(1)) {
            System.out.println("Too many requests, try again later");
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "Too many requests, try again later");
        }
        MyRecipe myRecipe = openAIService.makeRequest(ingredients, SYSTEM_MESSAGE);
        return myRecipe;
    }
    /**
     * Saves a recipe based on the provided {@code RecipeRequest}.
     *
     * @param recipeBody The {@code RecipeRequest} containing information about the recipe to be saved.
     * @param principal  The authenticated principal (user) making the request.
     */

    @PostMapping("/save-recipe")
    public ResponseStatusException saveRecipe(@RequestBody RecipeRequest recipeBody, Principal principal) {
        return recipeService.saveRecipe(recipeBody, principal);
    }

    @PostMapping("/admin")
    public ResponseStatusException saveRecipeAdmin(@RequestBody RecipeRequest recipeBody, Principal principal) {
        return recipeService.saveRecipeAdmin(recipeBody, principal);
    }
    /**
     * Retrieves all recipes in the system.
     *
     * @return A list of {@code RecipeResponse} representing the recipes.
     */
    @GetMapping("/admin")
    public List<RecipeResponse> getRecipes(Principal principal) {
       return recipeService.getAllRecipes(principal);
    }

    /**
     * Updates a recipe based on the provided {@code RecipeRequest}.
     *
     * @param recipeRequest The {@code RecipeRequest} containing information about the recipe to be updated.
     * @return A {@code RecipeResponse} representing the result of the update operation.
     */
    @PatchMapping("/admin")
    public RecipeResponse updateRecipe(@RequestBody RecipeRequest recipeRequest) {
        return recipeService.updateRecipe(recipeRequest);
    }

    /**
     * Deletes a recipe based on the provided {@code RecipeRequest}.
     *
     * @param recipeRequest The {@code RecipeRequest} containing information about the recipe to be deleted.
     * @return A {@code RecipeResponse} representing the result of the delete operation.
     */
    @DeleteMapping("/admin")
    public RecipeResponse deleteRecipe(@RequestBody RecipeRequest recipeRequest) {
        return recipeService.deleteRecipe(recipeRequest);
    }
    @GetMapping("/user-as-authenticated")
    public List<RecipeResponse> getAllRecipes(Principal principal) {
        return recipeService.getAllRecipes(principal);
    }
}
