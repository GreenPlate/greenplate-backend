package dk.kea.project.service;

import dk.kea.project.dto.ProductResponse;
import dk.kea.project.entity.Product;
import dk.kea.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class ProductService
	{
		@Autowired
		ProductRepository productRepository;

		//SallingService sallingService;
		public Page<ProductResponse> getProducts(Pageable pageable) {
		    List<ProductResponse> productResponses = productRepository.findAll().stream().map(ProductResponse::new).toList();
		    return productRepository.findAll(pageable).map(ProductResponse::new);
		}


		public void addProduct(Product product) {
		    productRepository.save(product);
		}
		public void addProducts(List<Product> products) {
		    productRepository.saveAll(products);
		}
	}

