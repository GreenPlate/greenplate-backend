package dk.kea.project.dto;

import dk.kea.project.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class StoreResponse {
	String id;
	String name;
	String brand;
	String zip;
	String city;
	String street;

	public StoreResponse(Store store) {
		this.id = store.getId();
		this.name = store.getName();
		this.brand = store.getBrand();
		this.zip = store.getZip();
		this.city = store.getCity();
		this.street = store.getStreet();
	}

}

