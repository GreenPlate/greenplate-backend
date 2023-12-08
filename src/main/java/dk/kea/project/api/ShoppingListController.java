package dk.kea.project.api;

import dk.kea.project.dto.ShoppingListRequest;
import dk.kea.project.dto.ShoppingListResponse;
import dk.kea.project.service.ShoppingListService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
/**
 * Controller class handling HTTP requests related to shopping lists.
 */
@RestController
@RequestMapping("/api/shopping-list")
public class ShoppingListController {
    ShoppingListService shoppingListService;
    /**
     * Constructor for the ShoppingListController class.
     *
     * @param shoppingListService The service responsible for handling shopping list operations.
     */
    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }
    /**
     * Handles the HTTP POST request to save a shopping list.
     *
     * @param body      The request body containing the shopping list details.
     * @param principal The authenticated user's principal object.
     * @return A ResponseStatusException indicating the success or failure of the operation.
     */
    @PostMapping("/save-shopping-list")
    public ResponseStatusException saveShoppingList(@RequestBody ShoppingListRequest body, Principal principal) {
        return shoppingListService.saveShoppingList(body, principal);
    }
    /**
     * Handles the HTTP GET request to retrieve all shopping lists for the authenticated user.
     *
     * @param principal The authenticated user's principal object.
     * @return A list of ShoppingListResponse objects representing the user's shopping lists.
     */
    @GetMapping("/user-as-authenticated")
    public List<ShoppingListResponse> getAllShoppingLists(Principal principal) {
        String username = principal.getName();
        return shoppingListService.findShoppingListsByUser(username);
    }
}
