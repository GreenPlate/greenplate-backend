package dk.kea.project.dto;

import dk.kea.project.entity.Offer;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class ShoppingListRequest {
    private List<Offer> offers;

    public ShoppingListRequest(List<Offer> offers) {
        this.offers = offers;
    }
}
