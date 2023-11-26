package dk.kea.project.dto;

import dk.kea.project.entity.Product;
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

		public ProductResponse (Product product) {
		    this.description = product.getDescription();
		    this.ean = product.getEan();
		    this.image = product.getImage();
		    this.originalPrice = product.getOriginalPrice();
		    this.newPrice = product.getNewPrice();
		    this.discount = product.getDiscount();
		    this.percentDiscount = product.getPercentDiscount();
		    this.category = product.getCategory();
		}
	}
