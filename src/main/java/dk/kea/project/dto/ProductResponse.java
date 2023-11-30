package dk.kea.project.dto;

import dk.kea.project.entity.Offer;
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

		public ProductResponse ( Offer offer)
		{
		    this.description = offer.getProduct().getDescription();
		    this.ean = offer.getProduct().getEan();
		    this.image = offer.getProduct().getImage();
		    this.originalPrice = offer.getOriginalPrice();
		    this.newPrice = offer.getNewPrice();
		    this.discount = offer.getDiscount();
		    this.percentDiscount = offer.getPercentDiscount();
		}
	}
