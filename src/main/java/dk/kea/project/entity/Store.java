package dk.kea.project.entity;

import dk.kea.project.dto.SallingStoreResponse;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing a store.
 * <p>
 * This class is mapped to the database table and is used to store information
 * about stores, including the unique identifier, name, brand, address (zip, city, street).
 * </p>
 *
 * @see SallingStoreResponse
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Store {

	/**
	 * The unique identifier for the store.
	 */
	@Id
	String id;

	/**
	 * The name of the store.
	 */
	String name;

	/**
	 * The brand associated with the store.
	 */
	String brand;

	/**
	 * The ZIP code of the store's location.
	 */
	String zip;

	/**
	 * The city where the store is located.
	 */
	String city;

	/**
	 * The street address of the store.
	 */
	String street;

	/**
	 * Constructs a {@code Store} object based on a {@link SallingStoreResponse} DTO.
	 *
	 * @param sallingStoreResponse The SallingStoreResponse DTO from which to populate the store entity.
	 */
	public Store(SallingStoreResponse sallingStoreResponse) {
		this.id = sallingStoreResponse.getId();
		this.name = sallingStoreResponse.getName();
		this.brand = sallingStoreResponse.getBrand();
		this.zip = sallingStoreResponse.getAddress().getZip();
		this.city = sallingStoreResponse.getAddress().getCity();
		this.street = sallingStoreResponse.getAddress().getStreet();
	}
}
