package dk.kea.project.entity;

import dk.kea.project.dto.SallingResponse;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Product
	{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		int id;
		 String description;
		 String ean;
		String image;
		String category;
		double originalPrice;
		double newPrice;
		double discount;
		double percentDiscount;
		@ManyToOne
		@JoinColumn(name = "requestId")
		int requestId;

		public Product(SallingResponse sallingResponse) {
		    this.description = sallingResponse.clearances.get(0).getDescription();
		    this.ean = clearance.getEan();
		    this.image = clearance.getImage();
		    this.category = clearance.getCategory();
		    this.originalPrice = clearance.getOriginalPrice();
		    this.newPrice = clearance.getNewPrice();
		    this.discount = clearance.getDiscount();
		    this.percentDiscount = clearance.getPercentDiscount();
		    this.requestId = clearance.getRequestId();
		}
	}
