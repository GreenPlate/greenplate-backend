package dk.kea.project.dto;

import dk.kea.project.entity.Offer;
import dk.kea.project.entity.ShoppingList;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingListResponse {
    private List<Offer> offers;

    public ShoppingListResponse(ShoppingList shoppingList) {
        this.offers = shoppingList.getOffers();
    }
}
