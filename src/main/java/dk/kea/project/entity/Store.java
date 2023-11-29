package dk.kea.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Store {
	@Id
	String id;
	String name;
	String brand;
	String zip;
	String city;
	String street;
}