package dk.kea.project.dto;

import dk.kea.project.entity.Offer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a response for a product.
 * <p>
 * This class is used to encapsulate product information for API responses.
 * It can be converted from the corresponding {@link Offer} entity.
 * </p>
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

	/**
	 * The description of the product.
	 */
	public String description;

	/**
	 * The EAN (European Article Number) code of the product.
	 */
	public String ean;

	/**
	 * The URL or path to the product image.
	 */
	public String image;

	/**
	 * The original price of the product.
	 */
	public double originalPrice;

	/**
	 * The new (discounted) price of the product.
	 */
	public double newPrice;

	/**
	 * The absolute discount amount on the product.
	 */
	public double discount;

	/**
	 * The percentage discount applied to the product.
	 */
	public double percentDiscount;

	/**
	 * The unique identifier for the product response.
	 */
	public int id;

	/**
	 * Constructs a {@code ProductResponse} object based on an {@link Offer} entity.
	 *
	 * @param offer The offer entity from which to populate the DTO.
	 */
	public ProductResponse(Offer offer) {
		this.description = offer.getProduct().getDescription();
		this.ean = offer.getProduct().getEan();
		this.image = offer.getProduct().getImage();
		this.originalPrice = offer.getOriginalPrice();
		this.newPrice = offer.getNewPrice();
		this.discount = offer.getDiscount();
		this.percentDiscount = offer.getPercentDiscount();
		this.id = offer.getId();
	}
}
