package dk.kea.project.dto;

import dk.kea.project.entity.Offer;
import dk.kea.project.entity.ShoppingList;
import dk.kea.project.entity.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingListResponse {
    private int id;
    private List<Offer> offers;
    private User user;
    private LocalDateTime createdAt;

    public ShoppingListResponse(ShoppingList shoppingList) {
        this.id = shoppingList.getId();
        this.offers = shoppingList.getOffers();
        this.user = shoppingList.getUser();
        this.createdAt = shoppingList.getCreatedAt();
    }
}
