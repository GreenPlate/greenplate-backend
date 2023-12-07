package dk.kea.project.repository;

import dk.kea.project.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
	List<Product> findAllByOrderByDescriptionAsc();
//		List<Product> findAllByStoreId(String storeId);
//		Page<Product> findAllByStoreId(String storeId, Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT p.description AS name, p.ean AS ean, COUNT(*) AS count " +
			"FROM Product p " +
			"JOIN Offer o ON p.ean = o.ean " +
			"JOIN recipe_offers ro ON o.id = ro.offers_id " +
			"GROUP BY p.ean")
	List<Object[]> getProductCount();
}
