package dk.kea.project.service;

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
/**
 * Service class responsible for managing products and offers.
 * This service interacts with the database to retrieve, save, and process product-related information.
 *

 */

@Service
public class ProductService {

	ProductRepository productRepository;

	RequestRepository requestRepository;
	OfferRepository offerRepository;

	SallingService sallingService;
	/**
	 * Constructs a new ProductService with the necessary repositories and services.
	 *
	 * @param productRepository   The repository for managing products.
	 * @param requestRepository   The repository for managing requests.
	 * @param offerRepository     The repository for managing offers.
	 * @param sallingService      The SallingService for handling Salling-related operations.
	 */

	public ProductService(ProductRepository productRepository, RequestRepository requestRepository,
								 OfferRepository offerRepository, SallingService sallingService) {
		this.productRepository = productRepository;
		this.requestRepository = requestRepository;
		this.offerRepository = offerRepository;
		this.sallingService = sallingService;
	}
	/**
	 * Retrieves product responses based on the specified request ID.
	 *
	 * @param requestId The ID of the request associated with the products.
	 * @return A list of {@code ProductResponse} representing the products.
	 */

	public List<ProductResponse> getProducts(int requestId) {

		return offerRepository.findAllByRequest_Id(requestId).stream().map(ProductResponse::new).collect(Collectors.toList());
	}
	/**
	 * Adds a product to the database.
	 *
	 * @param product The product to be added.
	 */

	public void addProduct(Product product) {
		productRepository.save(product);
	}
	/**
	 * Adds a list of offers to the database.
	 *
	 * @param offers The list of offers to be added.
	 */
	public void addOffers(List<Offer> offers) {
		offerRepository.saveAll(offers);
	}
	/**
	 * Retrieves offers from Salling, processes the data, and saves the offers to the database.
	 *
	 * @param storeId The ID of the store for which offers are retrieved.
	 * @param request The associated request for the retrieved offers.
	 */
	public void getOffersfromSallingAndSave(String storeId, Request request) {
		List<SallingResponse> sallingResponse = sallingService.getFoodWaste(storeId);
		List<Offer> offers = sallingResponse.stream().flatMap(salling -> salling.getClearances().stream()).map(clearance -> new Offer(clearance.getOffer().getOriginalPrice(), clearance.getOffer().getNewPrice(), clearance.getOffer().getDiscount(), clearance.getOffer().getPercentDiscount(), new Product(clearance.getProduct().ean, clearance.getProduct().description, clearance.getProduct().image), request)).collect(Collectors.toList());
		productRepository.saveAll(offers.stream().map(Offer::getProduct).collect(Collectors.toList()));
		offerRepository.saveAll(offers);

	}
}

