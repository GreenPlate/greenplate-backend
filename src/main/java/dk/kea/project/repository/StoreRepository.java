package dk.kea.project.repository;

import dk.kea.project.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, String> {
		List<Store> findAllByZip(String zipcode);
		Store findStoreById(String id);
}

