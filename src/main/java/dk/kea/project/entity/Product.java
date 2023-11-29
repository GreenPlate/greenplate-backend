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
}