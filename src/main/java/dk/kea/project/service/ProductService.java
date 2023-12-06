package dk.kea.project.service;

import dk.kea.project.dto.ProductCountResponse;
import dk.kea.project.dto.ProductResponse;
import dk.kea.project.dto.SallingResponse;
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
public class ProductService {

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

		return offerRepository.findAllByRequest_Id(requestId).stream().map(ProductResponse::new).collect(Collectors.toList());
	}


	public void addProduct(Product product) {
		productRepository.save(product);
	}

	public void addOffers(List<Offer> offers) {
		offerRepository.saveAll(offers);
	}

	public void getOffersfromSallingAndSave(String storeId, Request request) {
		List<SallingResponse> sallingResponse = sallingService.getFoodWaste(storeId);
		List<Offer> offers = sallingResponse.stream().flatMap(salling -> salling.getClearances().stream()).map(clearance -> new Offer(clearance.getOffer().getOriginalPrice(), clearance.getOffer().getNewPrice(), clearance.getOffer().getDiscount(), clearance.getOffer().getPercentDiscount(), new Product(clearance.getProduct().ean, clearance.getProduct().description, clearance.getProduct().image), request)).collect(Collectors.toList());
		productRepository.saveAll(offers.stream().map(Offer::getProduct).collect(Collectors.toList()));
		offerRepository.saveAll(offers);

	}

	public List<ProductCountResponse> getProductCount(){
		List<Object[]> result = productRepository.getProductCount();

		return result.stream()
				.map(row -> new ProductCountResponse(
						(String) row[0], // Assuming productEan is of type String
						(String) row[1], // Assuming productName is of type String
						((Number) row[2]).longValue() // Assuming usageCount is of type Long
				))
				.collect(Collectors.toList());
	}
}

