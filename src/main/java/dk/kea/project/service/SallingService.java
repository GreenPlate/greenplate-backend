package dk.kea.project.service;

import dk.kea.project.dto.SallingResponse;
import dk.kea.project.dto.SallingStoreResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Service class responsible for interacting with Salling Group's API.
 * This service provides methods to retrieve information about stores, food waste, and generate random ingredients.
 *
 *
 */

@Service
public class SallingService{
    private final static String SALLING_API_URL_V1 = "https://api.sallinggroup.com/v1";
    private final static String SALLING_API_URL_V2 = "https://api.sallinggroup.com/v2";

    @Value("${SALLING_API_KEY}")
    private String SALLING_API_KEY;

    private WebClient webClient;
    /**
     * Constructs a new SallingService and initializes the WebClient.
     */
    public SallingService() {
        this.webClient = WebClient.create();
    }
    /**
     * Retrieves a list of SallingStoreResponse objects based on the provided zipcode.
     *
     * @param zipcode The zipcode used to filter stores.
     * @return A filtered list of SallingStoreResponse objects.
     */
    public List<SallingStoreResponse> getStores(String zipcode){
        List<SallingStoreResponse> stores = webClient.method(HttpMethod.GET)
                .uri(SALLING_API_URL_V2 + "/stores?zip="+ zipcode)
                .header("Authorization", "Bearer " + SALLING_API_KEY)
                .retrieve()
                .bodyToFlux(SallingStoreResponse.class)
                .collectList()
                .doOnError(e -> System.out.println(e.getMessage()))
                .block();
        List<String> desiredBrands = Arrays.asList("netto", "bilka", "foetex");

        List<SallingStoreResponse> filteredStores = stores.stream()
                .filter(store -> desiredBrands.contains(store.getBrand()))
                .toList();
        return filteredStores;

    }
    /**
     * Retrieves a list of SallingResponse objects representing food waste for a specific store.
     *
     * @param id The ID of the store for which food waste information is retrieved.
     * @return A list of SallingResponse objects containing information about food waste.
     */

    public List<SallingResponse> getFoodWaste(String id){
        List<SallingResponse> products =
        webClient.method(HttpMethod.GET)
                .uri(SALLING_API_URL_V1 + "/food-waste/" + id)
                .header("Authorization", "Bearer " + SALLING_API_KEY)
                .retrieve()
                .bodyToFlux(SallingResponse.class)
                .collectList()
                .doOnError(e -> System.out.println(e.getMessage()))
                .block();


                return products;
    }
    /**
     * Generates a string of random ingredients based on food waste information.
     *
     * @param storeId The ID of the store used to fetch food waste information.
     * @return A string containing randomly selected ingredients.
     */

    public String ingredients(String storeId){
        List<SallingResponse> products=getFoodWaste(storeId);
        String ingredients = products.stream()
              .flatMap(sallingResponse -> sallingResponse.getClearances().stream()
                    .map(clearance -> clearance.getProduct().getDescription()))
              .collect(Collectors.joining(", "));
        List<String> ingredientsList = Arrays.asList(ingredients.split(", "));
        Collections.shuffle(ingredientsList);
        List<String> selectedList = ingredientsList.subList(0, Math.min(10, ingredientsList.size()));
        String result = String.join(",", selectedList);
        return result;
    }
}
