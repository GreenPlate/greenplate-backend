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

		@JoinColumn(name = "requestId")
		int requestId;


	}
