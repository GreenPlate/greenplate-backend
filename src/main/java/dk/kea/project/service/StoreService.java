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
/**
 * Service class responsible for managing operations related to stores.
 * This service provides methods for adding stores, checking store existence, and retrieving store information.
 *
 *
 */
@Service
public class StoreService {
	StoreRepository storeRepository;
	/**
	 * Constructs a new StoreService with the specified StoreRepository.
	 *
	 * @param storeRepository The repository for managing Store entities.
	 */
	public StoreService(StoreRepository storeRepository) {
		this.storeRepository = storeRepository;
	}
	/**
	 * Adds new stores to the database, filtering out duplicates.
	 *
	 * @param stores The list of SallingStoreResponse objects to be added as stores.
	 */
	public void addStores(List<SallingStoreResponse> stores) {
		System.out.println("addStores()");
		List<Store> mappedStores = stores.stream().map(Store::new).collect(Collectors.toList());
		List<Store> oldStores = storeRepository.findAll();
		List<Store> filteredStores = filteredStore(mappedStores, oldStores);
		storeRepository.saveAll(filteredStores);
	}
	/**
	 * Filters out stores that already exist in the database, returning only new stores.
	 *
	 * @param mappedStores The list of stores to be filtered and added.
	 * @param oldStores    The list of existing stores in the database.
	 * @return A list of new stores not present in the database.
	 */
	public List<Store> filteredStore(List<Store> mappedStores, List<Store> oldStores){
		List<Store> updatedStores = mappedStores.stream()
				.filter(mappedStore -> oldStores.stream().noneMatch(oldStore -> oldStore.getId().equals(mappedStore.getId())))
				.collect(Collectors.toList());
		return updatedStores;
	}
	/**
	 * Checks if stores with a specific zipcode exist in the database.
	 *
	 * @param zipcode The zipcode used to check store existence.
	 * @return {@code true} if stores exist, {@code false} otherwise.
	 */
	public boolean doesStoresExist(String zipcode){
		List<Store> stores = storeRepository.findAllByZip(zipcode);
		if(stores.isEmpty()){
			return false;
		}
		return true;
	}
	/**
	 * Retrieves a list of StoreResponse objects based on the provided zipcode.
	 *
	 * @param zipcode The zipcode used to filter stores.
	 * @return A list of StoreResponse objects representing stores in the specified zipcode.
	 */

	public List<StoreResponse> getStores(String zipcode){
		List<StoreResponse> stores = storeRepository.findAllByZip(zipcode).stream().map(StoreResponse::new).collect(Collectors.toList());
		return stores;
	}
	/**
	 * Finds a store by its unique identifier (ID).
	 *
	 * @param id The unique identifier of the store to be retrieved.
	 * @return The Store object corresponding to the specified ID.
	 */
	public Store findStoreById(String id){
		return storeRepository.findStoreById(id);
	}
	
}


