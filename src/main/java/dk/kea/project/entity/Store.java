package dk.kea.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Store
	{
		@Id
		String storeId;
		String name;
		String address;
		String zipcode;

	}
