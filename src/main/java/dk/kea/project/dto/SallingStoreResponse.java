package dk.kea.project.dto;

import dk.kea.project.entity.Store;
import lombok.*;

/**
 * Data Transfer Object (DTO) representing a response from Salling Group's API for a store.
 * <p>
 * This class is used to transfer data received as a response from Salling Group's API
 * for store information. It includes details such as the store ID, brand, name, and address.
 * </p>
 * <p>
 * The inner static class {@link Address} represents the address information of the store.
 * </p>
 * <p>
 * The class is annotated with {@link Getter}, {@link Setter}, {@link Builder},
 * {@link NoArgsConstructor}, and {@link AllArgsConstructor} from the Lombok library for automatic
 * generation of getters, setters, builder methods, and constructors.
 * </p>
 * <p>
 * The method {@code SallingStoreResponse(Store store)} is a constructor method that initializes
 * the fields of the DTO with data from a {@link Store} entity.
 * </p>
 *
 *
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SallingStoreResponse {

   /**
    * The store ID.
    */
   private String id;

   /**
    * The brand of the store.
    */
   private String brand;

   /**
    * The name of the store.
    */
   private String name;

   /**
    * The address information of the store.
    */
   private Address address;

   /**
    * Inner static class representing the address of the store.
    */
   @Getter
   @Setter
   @NoArgsConstructor
   public static class Address {
      /**
       * The city where the store is located.
       */
      public String city;

      /**
       * The street where the store is located.
       */
      public String street;

      /**
       * The ZIP code of the store's location.
       */
      public String zip;
   }

   /**
    * Constructor method that initializes the fields of the DTO with data from a {@link Store} entity.
    *
    * @param store The Store entity from which to extract data.
    */
   public void SallingStoreResponse(Store store) {
      this.id = store.getId();
      this.brand = store.getBrand();
      this.name = store.getName();
      this.address = new Address();
      this.address.city = store.getCity();
      this.address.street = store.getStreet();
      this.address.zip = store.getZip();
   }
}
