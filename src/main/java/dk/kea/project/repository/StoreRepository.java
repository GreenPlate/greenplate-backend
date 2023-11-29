package dk.kea.project.repository;

import dk.kea.project.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, String>
{

	}
