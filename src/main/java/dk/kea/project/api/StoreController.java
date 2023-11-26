package dk.kea.project.api;

import dk.kea.project.dto.ProductResponse;
import dk.kea.project.dto.SallingResponse;
import dk.kea.project.dto.StoreResponse;
import dk.kea.project.entity.Product;
import dk.kea.project.entity.Request;
import dk.kea.project.repository.RequestRepository;
import dk.kea.project.service.ProductService;
import dk.kea.project.service.SallingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {
    SallingService sallingService;
    ProductService productService;
    RequestRepository requestRepository;
    public StoreController(SallingService sallingService, ProductService productService, RequestRepository requestRepository) {
        this.productService = productService;
        this.requestRepository = requestRepository;
        this.sallingService = sallingService;
    }
    @GetMapping
    public List<StoreResponse> getStores(@RequestParam int zipcode){
        return sallingService.getStores(zipcode);
    }

    @GetMapping("/foodwaste")
    public List<SallingResponse> getFoodWaste(@RequestParam String id){
        // kald salling service brug  List<SallingResponse> i ny servi


        return sallingService.getFoodWaste(id);
    }
    @GetMapping("/clearance")
    public Page<ProductResponse> getProducts(@RequestParam String id, Pageable page){
        // check om request er gyldig:
        if (productService.checkRequest(id)){
            return productService.getProducts(id, page);
        }
        else{
            Request request = new Request();
            request.setStoreId(id);
            request.setCreated(LocalDateTime.now());
            requestRepository.save(request);
            productService.addRequest(id);

            return productService.getProducts(id, page);

        }
    }
    private List <Product> convertSallingResponse(SallingResponse sallingResponse){
        List<Product> products=sallingResponse.clearances.stream().map(Product::new).toList();
        return products;
    }
}

