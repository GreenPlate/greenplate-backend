package dk.kea.project.repository;

import dk.kea.project.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface RequestRepository extends JpaRepository<Request, Integer>	{
		boolean existsByStoreId(String id);
		Request findByStoreId(String id);
		Request findRequestByStoreIdAndCreatedIsAfter(String id, LocalDateTime nowMinus15);
	}
