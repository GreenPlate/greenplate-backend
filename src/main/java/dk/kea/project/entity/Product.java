package dk.kea.project.entity;

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
	}
