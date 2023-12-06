package dk.kea.project.entity;

import dk.kea.project.dto.ProductResponse;
import dk.kea.project.dto.SallingResponse;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing an offer on a product.
 * <p>
 * This class is mapped to the database table and is used to store information
 * about product offers, including pricing details, discounts, and associated products.
 * </p>
 *
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Offer {

	/**
	 * The unique identifier for the offer.
	 */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;

	/**
	 * The original price of the product before any discounts.
	 */
	private double originalPrice;

	/**
	 * The new (discounted) price of the product.
	 */
	private double newPrice;

	/**
	 * The absolute discount amount applied to the product.
	 */
	private double discount;

	/**
	 * The percentage discount applied to the product.
	 */
	private double percentDiscount;

	/**
	 * The associated product for which the offer is made.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ean")
	Product product;

	/**
	 * The request associated with this offer.
	 */
	@ManyToOne
	@JoinColumn(name = "request_id")
	Request request;

	/**
	 * Constructs an {@code Offer} object with the specified pricing details.
	 *
	 * @param originalPrice  The original price of the product.
	 * @param newPrice       The new (discounted) price of the product.
	 * @param discount       The absolute discount amount applied to the product.
	 * @param percentDiscount The percentage discount applied to the product.
	 */
	public Offer(double originalPrice, double newPrice, double discount, double percentDiscount) {
		this.originalPrice = originalPrice;
		this.newPrice = newPrice;
		this.discount = discount;
		this.percentDiscount = percentDiscount;
	}

	/**
	 * Constructs an {@code Offer} object with the specified pricing details,
	 * associated product, and request.
	 *
	 * @param originalPrice  The original price of the product.
	 * @param newPrice       The new (discounted) price of the product.
	 * @param discount       The absolute discount amount applied to the product.
	 * @param percentDiscount The percentage discount applied to the product.
	 * @param product        The associated product for which the offer is made.
	 * @param request        The request associated with this offer.
	 */
	public Offer(double originalPrice, double newPrice, double discount, double percentDiscount, Product product, Request request) {
		this.originalPrice = originalPrice;
		this.newPrice = newPrice;
		this.discount = discount;
		this.percentDiscount = percentDiscount;
		this.product = product;
		this.request = request;
	}
}
