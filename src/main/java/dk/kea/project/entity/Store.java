package dk.kea.project.entity;

import dk.kea.project.dto.SallingStoreResponse;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.dialect.aggregate.DB2AggregateSupport;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Store {
	@Id
	String id;
	String name;
	String brand;
	String zip;
	String city;
	String street;

	public Store(SallingStoreResponse sallingStoreResponse) {
		this.id = sallingStoreResponse.getId();
		this.name = sallingStoreResponse.getName();
		this.brand = sallingStoreResponse.getBrand();
		this.zip = sallingStoreResponse.getAddress().getZip();
		this.city = sallingStoreResponse.getAddress().getCity();
		this.street = sallingStoreResponse.getAddress().getStreet();
	}
}
