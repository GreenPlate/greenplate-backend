package dk.kea.project.entity;

import dk.kea.project.dto.SallingResponse;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a product.
 * <p>
 * This class is mapped to the database table and is used to store information
 * about products, including the EAN (European Article Number) code,
 * description, and an image URL or path.
 * </p>
 *
 *
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

	/**
	 * The unique identifier for the product (EAN code).
	 */
	@Id
	private String ean;

	/**
	 * The description of the product.
	 */
	private String description;

	/**
	 * The URL or path to the product image.
	 */
	private String image;
}
