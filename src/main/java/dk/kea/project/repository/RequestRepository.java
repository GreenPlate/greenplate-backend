package dk.kea.project.repository;

import dk.kea.project.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Integer> {
	boolean existsByStoreId(String id);
	Request findByStoreId(String id);
	Request findRequestByStoreIdAndCreatedIsAfter(String id, LocalDateTime nowMinus15);
	@Query("SELECT s.id, s.name, COUNT(r) AS callCount " +
			"FROM Store s LEFT JOIN Request r ON s.id = r.store.id " +
			"GROUP BY s.id, s.name")
	List<Object[]> countStoreCalls();

	@Query("SELECT s.zip, COUNT(r) AS callCount " +
			"FROM Store s LEFT JOIN Request r ON s.id = r.store.id " +
			"GROUP BY s.zip")
	List<Object[]> countZipcodeCalls();
}


