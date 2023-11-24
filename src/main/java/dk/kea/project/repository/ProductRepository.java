package dk.kea.project.repository;

import dk.kea.project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>
	{
	}
