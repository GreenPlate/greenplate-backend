package dk.kea.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Offer {
	@GeneratedValue (strategy= GenerationType.IDENTITY)
	@Id
	private int id;
	private double originalPrice;
	private double newPrice;
	private double discount;
	private double percentDiscount;

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(name="ean")
	Product product;

	@ManyToOne
	@JoinColumn(name = "request_id")
	Request request;



	public Offer(double originalPrice, double newPrice, double discount, double percentDiscount){

		this.originalPrice = originalPrice;
		this.newPrice = newPrice;
		this.discount = discount;
		this.percentDiscount = percentDiscount;
	}

}