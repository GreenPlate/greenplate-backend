package dk.kea.project.config;

import dk.kea.project.dto.SallingStoreResponse;
import dk.kea.project.entity.*;
import dk.kea.project.repository.*;
import dk.kea.security.entity.Role;
import jakarta.persistence.Id;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DeveloperData implements ApplicationRunner {
    UserRepository userRepository;
    RecipeRepository recipeRepository;
    StoreRepository storeRepository;
    RequestRepository requestRepository;
    OfferRepository offerRepository;
    ProductRepository productRepository;
    ShoppingListRepository shoppingListRepository;

    public DeveloperData(UserRepository userRepository, RecipeRepository recipeRepository,
                         StoreRepository storeRepository, RequestRepository requestRepository,
                         OfferRepository offerRepository, ProductRepository productRepository,
                         ShoppingListRepository shoppingListRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
        this.storeRepository = storeRepository;
        this.requestRepository = requestRepository;
        this.offerRepository = offerRepository;
        this.productRepository = productRepository;
        this.shoppingListRepository = shoppingListRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {

    
    }
}
