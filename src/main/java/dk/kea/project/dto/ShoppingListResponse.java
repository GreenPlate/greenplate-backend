package dk.kea.project.dto;

import dk.kea.project.entity.Offer;
import dk.kea.project.entity.ShoppingList;
import dk.kea.project.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
/**
 * Data Transfer Object (DTO) representing a response containing details of a shopping list.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingListResponse {
    /**
     * Unique identifier for the shopping list.
     */
    private int id;
    /**
     * List of offers included in the shopping list.
     */
    private List<Offer> offers;
    /**
     * User associated with the shopping list.
     */
    private User user;
    /**
     * Date and time when the shopping list was created.
     */
    private LocalDateTime createdAt;
    /**
     * Constructor for the ShoppingListResponse class.
     *
     * @param shoppingList The ShoppingList entity from which to create the response.
     */
    public ShoppingListResponse(ShoppingList shoppingList) {
        this.id = shoppingList.getId();
        this.offers = shoppingList.getOffers();
        this.user = shoppingList.getUser();
        this.createdAt = shoppingList.getCreatedAt();
    }
}
