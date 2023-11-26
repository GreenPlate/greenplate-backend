package dk.kea.project.repository;

import dk.kea.project.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Integer>
	{
		boolean existsByStoreId(String id);

		Request findByStoreId(String id);
	}
