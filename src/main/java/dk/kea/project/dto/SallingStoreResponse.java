package dk.kea.project.dto;

import dk.kea.project.entity.Store;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SallingStoreResponse {
    private String id;
    private String brand;
    private String name;
    private Address address;

   @Getter
    @Setter
    @NoArgsConstructor
     public static class Address{
        public String city;
        public String street;
        public String zip;
    }
    public void SallingStoreResponse(Store store){
        this.id = store.getId();
        this.brand = store.getBrand();
        this.name = store.getName();
        this.address = new Address();
        this.address.city = store.getCity();
        this.address.street = store.getStreet();
        this.address.zip = store.getZip();
    }
}
