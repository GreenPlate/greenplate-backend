package dk.kea.project.entity;

import dk.kea.project.dto.SallingResponse;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
	@Id
	private String ean;
	private String description;
	private String image;

//	public Product(SallingResponse sallingResponse){
//		this.ean = sallingResponse.clearances.get(0).product.getEan();
//		this.description = sallingResponse.clearances.get(0).product.getDescription();
//		this.image = sallingResponse.clearances.get(0).product.getImage();
//	}
}