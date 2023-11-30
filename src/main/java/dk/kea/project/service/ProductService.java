package dk.kea.project.service;

import dk.kea.project.dto.ProductResponse;
import dk.kea.project.entity.Offer;
import dk.kea.project.entity.Product;
import dk.kea.project.entity.Request;
import dk.kea.project.repository.OfferRepository;
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

		ProductRepository productRepository;

		RequestRepository requestRepository;
		OfferRepository offerRepository;

		SallingService sallingService;

		public ProductService(ProductRepository productRepository, RequestRepository requestRepository,
									 OfferRepository offerRepository, SallingService sallingService) {
			this.productRepository = productRepository;
			this.requestRepository = requestRepository;
			this.offerRepository = offerRepository;
			this.sallingService = sallingService;
		}

		public List<ProductResponse> getProducts(int requestId) {

		  return offerRepository.findAllByRequest_Id().stream().map(ProductResponse::new).collect(Collectors.toList());
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



	}

