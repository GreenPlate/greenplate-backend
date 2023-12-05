package dk.kea.project.api;

import dk.kea.project.dto.*;
import dk.kea.project.entity.Offer;
import dk.kea.project.entity.Product;
import dk.kea.project.entity.Request;
import dk.kea.project.entity.Store;
import dk.kea.project.repository.RequestRepository;
import dk.kea.project.service.ProductService;
import dk.kea.project.service.RequestService;
import dk.kea.project.service.SallingService;
import dk.kea.project.service.StoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    SallingService sallingService;
    ProductService productService;
    RequestRepository requestRepository;
    StoreService storeService;
    RequestService requestService;


    public StoreController(SallingService sallingService,
                           ProductService productService,
                           RequestRepository requestRepository,
                           StoreService storeService,
                           RequestService requestService) {
        this.storeService = storeService;
        this.productService = productService;
        this.requestRepository = requestRepository;
        this.sallingService = sallingService;
        this.requestService = requestService;
    }

    @GetMapping
    public List<StoreResponse> getStores(@RequestParam String zipcode) {
        boolean doesStoreExist = storeService.doesStoresExist(zipcode);
        if (!doesStoreExist) {
            List<SallingStoreResponse> storesFromZip = sallingService.getStores(zipcode);
            storeService.addStores(storesFromZip);
        }
        return storeService.getStores(zipcode);
    }

    @GetMapping("/clearance")
    public List<ProductResponse> getProducts(@RequestParam String id){
        // tjek if request is valid

        if(requestService.checkRequest(id)){
            Request request = requestService.findRequestByStoreIdAndCreatedIsAfter(id, LocalDateTime.now().minusMinutes(15));
            return productService.getProducts(request.getId());
        }
        // else new request to Salling, persist in DB, retrieve from DB
        Request request = new Request(storeService.findStoreById(id));
         requestService.addRequest(request);

        productService.getOffersfromSallingAndSave(id, request);
        return productService.getProducts(request.getId());
    }
//    public List<ProductResponse> getProducts(@RequestParam String id){
//        // check if request still is valid:
//        if (productService.checkRequest(id)){
//            return productService.getProducts(id);
//        }
//        else{
//            //persist request in database: create new request
//            Request request = new Request();
//            request.setStoreId(id);
//            LocalDateTime now = LocalDateTime.now();
//            // request.setCreated(now);
//            productService.addRequest(request);
//            int requestId = productService.findNewestRequest(id,now.minusMinutes(15)).getRequestId();
//            List<SallingResponse> sallingResponse = sallingService.getFoodWaste(id);
//            convertSallingResponse(sallingResponse, requestId, id);
////            productService.addProducts(products);
//
//
//        }
//        return productService.getProducts(id);
//    }
//    private void convertSallingResponse(List<SallingResponse> sallingResponse,int requestId, String storeId) {
//        List<Product> products= new ArrayList<>();
//        sallingResponse.get(0).clearances.forEach(clearance -> {
//            Product product = new Product();
//            if (clearance.product.category == null){
//                product.setCategory("None");
//            }
////            product.setCategory(clearance.product.category[0] == null ? "None" : (clearance.product.category[0]));
//            product.setDescription(clearance.product.description==null ? "None" : (clearance.product.description));
//            product.setImage(clearance.product.image==null ? "None" : (clearance.product.image));
//            product.setOriginalPrice(clearance.offer.originalPrice ==0 ? 0.0 : (clearance.offer.originalPrice));
//            product.setDiscount(clearance.offer.discount==0 ? 0.0 : (clearance.offer.discount));
//            product.setEan(clearance.product.ean==null ? "None" : (clearance.product.ean));
//            product.setNewPrice(clearance.offer.newPrice==0 ? 0.0 : (clearance.offer.newPrice));
//            product.setPercentDiscount(clearance.offer.percentDiscount==0 ? 0.0 : (clearance.offer.percentDiscount));
//            product.setRequestId(requestId);
//            product.setStoreId(storeId);
//            products.add(product);
//        });
//        productService.addProducts(products);
//    }
    @GetMapping("/countstorecalls")
    public List<StoreCountResponse> getStoreCount(){
        System.out.println("countStoreCalls()");
        return requestService.countStoreCalls();
    }
    
    @GetMapping("/countzipcodecalls")
    public List<ZipcodeCountResponse> getZipcodeCount(){
        System.out.println("getZipcodeCount()");
        return requestService.countZipcodeCalls();
    }
}



