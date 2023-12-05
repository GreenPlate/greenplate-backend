package dk.kea.project.repository;

import dk.kea.project.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
//		List<Product> findAllByStoreId(String storeId);
//		Page<Product> findAllByStoreId(String storeId, Pageable pageable);

	@Query("SELECT p.description, p.ean, COUNT(o.id) " +
			"FROM Product p " +
			"LEFT JOIN Offer o ON p = o.product " +
			"GROUP BY p.description, p.ean")
	List<Object[]> getProductCount();


	
}
