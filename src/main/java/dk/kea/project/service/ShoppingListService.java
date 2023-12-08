package dk.kea.project.service;

import dk.kea.project.dto.ShoppingListRequest;
import dk.kea.project.dto.ShoppingListResponse;
import dk.kea.project.entity.Offer;
import dk.kea.project.entity.ShoppingList;
import dk.kea.project.repository.OfferRepository;
import dk.kea.project.repository.ShoppingListRepository;
import dk.kea.project.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
/**
 * Service class for managing shopping lists.
 */
@Service
public class ShoppingListService {
    ShoppingListRepository shoppingListRepository;
    UserRepository userRepository;
    OfferRepository offerRepository;
    /**
     * Constructor for the ShoppingListService class.
     *
     * @param shoppingListRepository The repository for shopping lists.
     * @param userRepository        The repository for users.
     * @param offerRepository       The repository for offers.
     */
    public ShoppingListService(ShoppingListRepository shoppingListRepository, UserRepository userRepository, OfferRepository offerRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
    }
    /**
     * Retrieves all shopping lists associated with a specific user.
     *
     * @param username The username of the user.
     * @return A list of ShoppingListResponse objects representing the user's shopping lists.
     */
    public List<ShoppingListResponse> findShoppingListsByUser(String username){
        List<ShoppingList> shoppingLists = shoppingListRepository.findAllByUserUsername(username);
        return shoppingLists.stream().map(shoppinglist -> new ShoppingListResponse(shoppinglist)).toList();
    }
    /**
     * Saves a new shopping list based on the provided request body.
     *
     * @param body      The request body containing details of the shopping list.
     * @param principal The principal object representing the authenticated user.
     * @return A ResponseStatusException indicating the success or failure of the operation.
     */
    public ResponseStatusException saveShoppingList(ShoppingListRequest body, Principal principal) {
        ShoppingList shoppingList = new ShoppingList();
        List<Offer> offers = body.getOffers().stream().map(offer -> offerRepository.findAllById(offer.getId())).toList();
        shoppingList.setOffers(offers);
        shoppingList.setUser(userRepository.findUserByUsername(principal.getName()));
        LocalDateTime now = LocalDateTime.now();
        shoppingList.setCreatedAt(now);
        shoppingListRepository.save(shoppingList);
        return new ResponseStatusException(HttpStatus.ACCEPTED, "Shoppinglist saved");
    }
}
