package dk.kea.project.service;

import dk.kea.project.entity.Store;
import dk.kea.project.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class StoreService {
	StoreRepository storeRepository;
	public StoreService(StoreRepository storeRepository) {
		this.storeRepository = storeRepository;
	}

	public void addStore(Store store) {
		storeRepository.save(store);
	}
	public void addStores(List<Store> stores) {
		storeRepository.saveAll(stores);
	}
}
