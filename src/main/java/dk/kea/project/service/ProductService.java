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
import java.util.stream.Collectors;

@Service
public class ProductService
	{
		@Autowired
		ProductRepository productRepository;
		@Autowired
		RequestRepository requestRepository;

		//SallingService sallingService;
		public List<ProductResponse> getProducts( String storeId) {
		    List<ProductResponse> productResponses = productRepository.findAllByStoreId(storeId).stream().map(ProductResponse::new).toList();
//		    return productRepository.findAllByStoreId(storeId, pageable).map(ProductResponse::new);
			return productRepository.findAllByStoreId(storeId).stream().map(ProductResponse::new).collect(Collectors.toList());
		}


		public void addProduct(Product product) {
		    productRepository.save(product);
		}
		public void addProducts(List<Product> products) {
		    productRepository.saveAll(products);
		}
		public void addRequest(Request request){
		    requestRepository.save(request);

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

			if(requestRepository.existsByStoreId(id) && requestRepository.findByStoreId(id).getExpires().isAfter(now)){
				requestExistsAndStillValid=true;
			}
		    return requestExistsAndStillValid;
		}
		public Request findNewestRequest(String id, LocalDateTime nowMinus15){
			return requestRepository.findRequestByStoreIdAndCreatedIsAfter(id, nowMinus15);
		}
	}

