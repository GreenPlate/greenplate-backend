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
		String storeId;
		@Builder
		public Product(String description, String ean, String image, String category, double originalPrice, double newPrice, double discount, double percentDiscount, String storeId) {
			this.description = description;
			this.ean = ean;
			this.image = image;
			this.category = category;
			this.originalPrice = originalPrice;
			this.newPrice = newPrice;
			this.discount = discount;
			this.percentDiscount = percentDiscount;
			this.storeId = storeId;
		}

		@JoinColumn(name = "requestId")
		int requestId;


	}
