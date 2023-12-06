package dk.kea.project.dto;

import dk.kea.project.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a response for a store.
 * <p>
 * This class is used to encapsulate store information for API responses.
 * It can be converted from the corresponding {@link Store} entity.
 * </p>
 *
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponse {

	/**
	 * The unique identifier for the store.
	 */
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
	 * Constructs a {@code StoreResponse} object based on a {@link Store} entity.
	 *
	 * @param store The store entity from which to populate the DTO.
	 */
	public StoreResponse(Store store) {
		this.id = store.getId();
		this.name = store.getName();
		this.brand = store.getBrand();
		this.zip = store.getZip();
		this.city = store.getCity();
		this.street = store.getStreet();
	}
}
