package dk.kea.project.service;

import dk.kea.project.dto.SallingResponse;
import dk.kea.project.dto.StoreCountResponse;
import dk.kea.project.dto.ZipcodeCountResponse;
import dk.kea.project.entity.Request;
import dk.kea.project.repository.RequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class responsible for managing requests and their associated data.
 * This service interacts with the database to retrieve, save, and process request-related information.
 *

 */

@Service
public class RequestService {
	RequestRepository requestRepository;
	StoreService storeService;

	/**
	 * Constructs a new RequestService with the necessary repositories and services.
	 *
	 * @param requestRepository The repository for managing requests.
	 * @param storeService      The StoreService for handling store-related operations.
	 */
public  RequestService(RequestRepository requestRepository, StoreService storeService){
	this.requestRepository = requestRepository;
	this.storeService = storeService;
}
	/**
	 * Checks if a request with the specified store ID exists and is still valid.
	 *
	 * @param id The ID of the store associated with the request.
	 * @return {@code true} if the request exists and is still valid; {@code false} otherwise.
	 */
	public boolean checkRequest(String id){
		LocalDateTime fifteenMinutesAgo = LocalDateTime.now().minusMinutes(15);
		return requestRepository.findRequestByStoreIdAndCreatedIsAfter(id, fifteenMinutesAgo) != null;
	}
	/**
	 * Finds the newest request for the specified store ID created after a given timestamp.
	 *
	 * @param id           The ID of the store associated with the request.
	 * @param nowMinus15   The timestamp indicating the time 15 minutes ago.
	 * @return The newest request for the store ID created after the given timestamp.
	 */
	public Request findNewestRequest(String id, LocalDateTime nowMinus15){
		return requestRepository.findRequestByStoreIdAndCreatedIsAfter(id, nowMinus15);
	}
	/**
	 * Finds a request for the specified store ID created after a given timestamp.
	 *
	 * @param id           The ID of the store associated with the request.
	 * @param nowMinus15   The timestamp indicating the time 15 minutes ago.
	 * @return The request for the store ID created after the given timestamp.
	 */
	public Request findRequestByStoreIdAndCreatedIsAfter(String id, LocalDateTime nowMinus15){
		return requestRepository.findRequestByStoreIdAndCreatedIsAfter(id, nowMinus15);
	}

	/**
	 * Adds a new request to the database.
	 *
	 * @param request The request to be added.
	 */
	public void addRequest(Request request){
		requestRepository.save(request);

	}

	public List<StoreCountResponse> countStoreCalls(){		
		List<Object[]> result = requestRepository.countStoreCalls();
		List<StoreCountResponse> storeCountResponses = new ArrayList<>();

		for (Object[] row : result) {
			String storeId = (String) row[0];
			String storeName = (String) row[1];
			Long storeCount = (Long) row[2];
			
			StoreCountResponse newResponse = new StoreCountResponse(storeId, storeName, storeCount);
			storeCountResponses.add(newResponse);
		}
		return storeCountResponses;
	}
	
	public List<ZipcodeCountResponse> countZipcodeCalls(){
		List<Object[]> result = requestRepository.countZipcodeCalls();
		List<ZipcodeCountResponse> zipcodeCountResponses = new ArrayList<>();
		
		for(Object[] row : result){
			String zipcode = (String) row[0];
			Long count = (Long) row[1];
			
			ZipcodeCountResponse newZipcodeCountResponse = new ZipcodeCountResponse(zipcode, count);
			zipcodeCountResponses.add(newZipcodeCountResponse);
		}
		
		return zipcodeCountResponses;
	}
}
