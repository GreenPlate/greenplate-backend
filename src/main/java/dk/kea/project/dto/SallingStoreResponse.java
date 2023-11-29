package dk.kea.project.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
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
}
