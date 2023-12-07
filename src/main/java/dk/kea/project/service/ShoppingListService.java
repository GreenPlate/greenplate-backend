package dk.kea.project.service;

import dk.kea.project.dto.ShoppingListRequest;
import dk.kea.project.dto.ShoppingListResponse;
import dk.kea.project.entity.Offer;
import dk.kea.project.entity.ShoppingList;
import dk.kea.project.repository.OfferRepository;
import dk.kea.project.repository.ShoppingListRepository;
import dk.kea.project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingListService {
    ShoppingListRepository shoppingListRepository;
    UserRepository userRepository;
    OfferRepository offerRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository, UserRepository userRepository, OfferRepository offerRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
    }

    public List<ShoppingListResponse> findShoppingListsByUser(String username){
        List<ShoppingList> shoppingLists = shoppingListRepository.findAllByUserUsername(username);
        return shoppingLists.stream().map(shoppinglist -> new ShoppingListResponse(shoppinglist)).toList();
    }

    public void saveShoppingList(ShoppingListRequest body, Principal principal) {
        ShoppingList shoppingList = new ShoppingList();
        List<Offer> offers = body.getOffers().stream().map(offer -> offerRepository.findAllById(offer.getId())).toList();
        shoppingList.setOffers(offers);
        shoppingList.setUser(userRepository.findUserByUsername(principal.getName()));
        LocalDateTime now = LocalDateTime.now();
        shoppingList.setCreatedAt(now);
        shoppingListRepository.save(shoppingList);
    }
}
