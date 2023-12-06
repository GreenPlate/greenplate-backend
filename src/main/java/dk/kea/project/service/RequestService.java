package dk.kea.project.service;

import dk.kea.project.dto.SallingResponse;
import dk.kea.project.entity.Request;
import dk.kea.project.repository.RequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
		boolean requestExistsAndStillValid = false;
		LocalDateTime now = LocalDateTime.now();

		if(requestRepository.existsByStoreId(id) && requestRepository.findByStoreId(id).getExpires().isAfter(now)){
			requestExistsAndStillValid=true;
		}
		return requestExistsAndStillValid;
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

}
