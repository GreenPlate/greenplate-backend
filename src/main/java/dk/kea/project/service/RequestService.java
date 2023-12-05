package dk.kea.project.service;

import dk.kea.project.dto.SallingResponse;
import dk.kea.project.dto.StoreCountResponse;
import dk.kea.project.entity.Request;
import dk.kea.project.repository.RequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {
	RequestRepository requestRepository;
	StoreService storeService;


public  RequestService(RequestRepository requestRepository, StoreService storeService){
	this.requestRepository = requestRepository;
	this.storeService = storeService;
}
	public boolean checkRequest(String id){
		boolean requestExistsAndStillValid = false;
		LocalDateTime now = LocalDateTime.now();

		if(requestRepository.existsByStoreId(id) && requestRepository.findByStoreId(id).getExpires().isAfter(now)){
			requestExistsAndStillValid=true;
		}
		return requestExistsAndStillValid;
	}
	public Request findNewestRequest(String id, LocalDateTime nowMinus15){
		return requestRepository.findRequestByStoreIdAndCreatedIsAfter(id, nowMinus15);
	}
	public Request findRequestByStoreIdAndCreatedIsAfter(String id, LocalDateTime nowMinus15){
		return requestRepository.findRequestByStoreIdAndCreatedIsAfter(id, nowMinus15);
	}
//	public List<SallingResponse> getFoodWaste(String id){
//		return requestRepository.findByStoreId(id);
//	}
	public void addRequest(Request request){
		requestRepository.save(request);

	}
//	// check if request still is valid:
//        if (productService.checkRequest(id)){
//		return productService.getProducts(id);
//	}
//        else{
//		//persist request in database: create new request
//		Request request = new Request();
//		request.setStore(storeService.findStoreById(id));
//		LocalDateTime now = LocalDateTime.now();
//		// request.setCreated(now);
//		productService.addRequest(request);
//		int requestId = productService.findNewestRequest(id,now.minusMinutes(15)).getRequestId();
//		List<SallingResponse> sallingResponse = sallingService.getFoodWaste(id);
//		convertSallingResponse(sallingResponse, requestId, id);
////            productService.addProducts(products);
//
//
//	}

	public List<StoreCountResponse> countStoreCalls(){
		int count = 0;
		
		List<Object[]> result = requestRepository.countStoreCalls();
		
		List<StoreCountResponse> storeCountResponses = new ArrayList<>();

		for (Object[] row : result) {
			// Assuming that the types are String for the first two elements
			String storeId = (String) row[0];
			String storeName = (String) row[1];
			Long storeCount = (Long) row[2];
			

			// Ensure you use the correct indices and types
			System.out.println("Store ID: " + storeId);
			System.out.println("Store Name: " + storeName);
			System.out.println("Store Count: " + storeCount);

			StoreCountResponse newResponse = new StoreCountResponse(storeId, storeName, storeCount);
			storeCountResponses.add(newResponse);
		}
		return storeCountResponses;
	}
	
}
