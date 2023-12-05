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

	public void addStores(List<SallingStoreResponse> stores) {
		System.out.println("addStores()");
		List<Store> mappedStores = stores.stream().map(Store::new).collect(Collectors.toList());
		List<Store> oldStores = storeRepository.findAll();
		List<Store> filteredStores = filteredStore(mappedStores, oldStores);
		storeRepository.saveAll(filteredStores);
	}
	public List<Store> filteredStore(List<Store> mappedStores, List<Store> oldStores){
		System.out.println("filteredStores()");

		List<Store> updatedStores = mappedStores.stream()
				.filter(mappedStore -> oldStores.stream().noneMatch(oldStore -> oldStore.getId().equals(mappedStore.getId())))
				.collect(Collectors.toList());
		return updatedStores;
	}
	public boolean doesStoresExist(String zipcode){
		List<Store> stores = storeRepository.findAllByZip(zipcode);
		if(stores.isEmpty()){
			return false;
		}
		return true;
	}

	public List<StoreResponse> getStores(String zipcode){
		List<StoreResponse> stores = storeRepository.findAllByZip(zipcode).stream().map(StoreResponse::new).collect(Collectors.toList());
		return stores;
	}
	public Store findStoreById(String id){
		return storeRepository.findStoreById(id);
	}
	
}


