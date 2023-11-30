package dk.kea.project.service;

import dk.kea.project.dto.ProductResponse;
import dk.kea.project.dto.SallingStoreResponse;
import dk.kea.project.dto.StoreResponse;
import dk.kea.project.entity.Store;
import dk.kea.project.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	public List<SallingStoreResponse> getStores(String zipcode){
		List<Store> stores = storeRepository.findByZipcode(zipcode);

		return stores.stream().map(store -> (new SallingStoreResponse(store))).toList();


//		return productRepository.findAllByStoreId(storeId).stream().map(ProductResponse::new).collect(Collectors.toList());

	}
}


