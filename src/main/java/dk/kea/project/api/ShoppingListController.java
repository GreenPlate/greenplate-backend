package dk.kea.project.api;

import dk.kea.project.dto.ShoppingListRequest;
import dk.kea.project.dto.ShoppingListResponse;
import dk.kea.project.service.ShoppingListService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/shopping-list")
public class ShoppingListController {
    ShoppingListService shoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PostMapping("/save-shopping-list")
    public ResponseStatusException saveShoppingList(@RequestBody ShoppingListRequest body, Principal principal) {
        return shoppingListService.saveShoppingList(body, principal);
    }
    @GetMapping("/user-as-authenticated")
    public List<ShoppingListResponse> getAllShoppingLists(Principal principal) {
        String username = principal.getName();
        return shoppingListService.findShoppingListsByUser(username);
    }
}
