package dk.kea.project.dto;

import dk.kea.project.entity.Offer;
import lombok.*;

import java.util.List;

/**
 * Data Transfer Object (DTO) representing a request to create a shopping list.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
public class ShoppingListRequest {
    /**
     * List of offers to be included in the shopping list.
     */
    private List<Offer> offers;
    /**
     * Constructor for the ShoppingListRequest class.
     *
     * @param offers List of offers to be included in the shopping list.
     */
    public ShoppingListRequest(List<Offer> offers) {
        this.offers = offers;
    }
}
