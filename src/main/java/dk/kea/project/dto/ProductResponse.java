package dk.kea.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductResponse
	{
		public String description;
		public String ean;
		public String image;
		public double originalPrice;
		public double newPrice;
		public double discount;
		public double percentDiscount;
		public String category;

		public ProductResponse(SallingResponse sallingResponse) {
			this.description = sallingResponse.clearances.get(0).product.description;
			this.ean = sallingResponse.clearances.get(0).product.ean;
			this.image = sallingResponse.clearances.get(0).product.image;
			this.originalPrice = sallingResponse.clearances.get(0).offer.originalPrice;
			this.newPrice = sallingResponse.clearances.get(0).offer.newPrice;
			this.discount = sallingResponse.clearances.get(0).offer.discount;
			this.percentDiscount = sallingResponse.clearances.get(0).offer.percentDiscount;
			this.category = sallingResponse.clearances.get(0).product.description;
		}
	}
