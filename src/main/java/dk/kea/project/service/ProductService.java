package dk.kea.project.service;

import dk.kea.project.dto.ProductResponse;
import dk.kea.project.entity.Product;
import dk.kea.project.entity.Request;
import dk.kea.project.repository.ProductRepository;
import dk.kea.project.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService
	{
		@Autowired
		ProductRepository productRepository;
		@Autowired
		RequestRepository requestRepository;

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
		public void addRequest(String id){
		    requestRepository.save(new Request().setStoreId(id););

		}

		/**
		 * This method checks if a request exists and is still valid
		 *
		 * @param id : StoreId
		 * @return boolean
		 */

		public boolean checkRequest(String id){
			boolean requestExistsAndStillValid = false;
			LocalDateTime now = LocalDateTime.now();

			if(!requestRepository.existsByStoreId(id) || requestRepository.findByStoreId(id).getExpires().isAfter(now)){
				requestExistsAndStillValid=true;
			}
		    return requestExistsAndStillValid;
		}
	}

