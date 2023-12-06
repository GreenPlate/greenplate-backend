package dk.kea.project.config;

import dk.kea.project.dto.SallingStoreResponse;
import dk.kea.project.entity.*;
import dk.kea.project.repository.*;
import dk.kea.security.entity.Role;
import jakarta.persistence.Id;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DeveloperData implements ApplicationRunner {
    UserRepository userRepository;
    RecipeRepository recipeRepository;
    StoreRepository storeRepository;
    RequestRepository requestRepository;
    OfferRepository offerRepository;
    ProductRepository productRepository;

    public DeveloperData(UserRepository userRepository, RecipeRepository recipeRepository,
                         StoreRepository storeRepository, RequestRepository requestRepository,
                         OfferRepository offerRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
        this.storeRepository = storeRepository;
        this.requestRepository = requestRepository;
        this.offerRepository = offerRepository;
        this.productRepository = productRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {

    // Create and save users
    User admin1 = new User("admin", "admin@admin", "password123", "firstName", "lastName");
    admin1.addRole(Role.ADMIN);
    userRepository.save(admin1);

    User user1 = new User("user", "user@user", "password123", "firstName", "lastName");
    user1.addRole(Role.USER);
    userRepository.save(user1);

    // Create and save stores
    Store store1 = new Store("id1", "Store 1", "Brand 1", "12345", "City 1", "Street 1");
    Store store2 = new Store("id2", "Store 2", "Brand 2", "67890", "City 2", "Street 2");
    storeRepository.saveAll(List.of(store1, store2));
      

    // Create and save requests
    Request request1 = new Request(store1);
    Request request2 = new Request(store2);
    requestRepository.saveAll(List.of(request1, request2));

    // Create and save products
    Product product1 = new Product("ean1", "Product 1", "Image 1");
    Product product2 = new Product("ean2", "Product 2", "Image 2");
    Product product3 = new Product("ean3", "Product 3", "Image 3");
    productRepository.saveAll(List.of(product1, product2, product3));

    // Create and save offers
    Offer offer1 = new Offer(10.0, 8.0, 2.0, 20.0, product1, request1);
    Offer offer2 = new Offer(20.0, 15.0, 5.0, 25.0, product2, request1);
    Offer offer3 = new Offer(15.0, 12.0, 3.0, 15.0, product3, request2);
    offerRepository.saveAll(List.of(offer1, offer2, offer3));

    // Create and save recipes
    Recipe recipe1 = new Recipe("Recipe 1", "Recipe body 1", user1, List.of(offer1, offer2));
    Recipe recipe2 = new Recipe("Recipe 2", "Recipe body 2", user1, List.of(offer1, offer3));
    Recipe recipe3 = new Recipe("Recipe 2", "Recipe body 2", admin1, List.of(offer1, offer3));
    Recipe recipe4 = new Recipe("Recipe 2", "Recipe body 2", admin1, List.of(offer1, offer3));
    recipeRepository.saveAll(List.of(recipe1, recipe2, recipe3, recipe4));





        //    Recipe recipe = new Recipe("Ostehapser", "Ostehapser er en lækker snack", "Ost, mel, æg, rasp", user1);
//    recipeRepository.save(recipe);
    }
}
