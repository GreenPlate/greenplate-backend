package dk.kea.project.api;

import dk.kea.project.dto.ProductCountResponse;
import dk.kea.project.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/count")
    public List<ProductCountResponse> getProductCount() {
        return productService.getProductCount();
    }
}
