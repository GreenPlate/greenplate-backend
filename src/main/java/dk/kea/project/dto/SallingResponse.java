package dk.kea.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Data Transfer Object (DTO) representing a response from Salling Group's API.
 * <p>
 * This class is used to transfer data received as a response from Salling Group's API.
 * It includes information such as a list of clearances, where each clearance contains
 * details about an offer and its associated product.
 * </p>
 * <p>
 * The inner static classes {@link Clearance}, {@link Offer}, and {@link Product}
 * represent a clearance, offer details, and product details respectively.
 * </p>
 * <p>
 * The class is annotated with {@link Getter}, {@link Setter}, and {@link NoArgsConstructor}
 * from the Lombok library for automatic generation of getters, setters, and a no-argument constructor.
 * </p>
 * <p>
 * The example response structure from Salling Group's API is provided as comments.
 * </p>
 *
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class SallingResponse {
    /**
     * This is the response from Salling Group's API. Example given below:
     * {
     *     "clearances": [
     *         {
     *             "offer": {
     *                 "originalPrice": 199.95,
     *                 "newPrice": 199.95,
     *                 "discount": 0,
     *                 "percentDiscount": 0
     *             },
     *             "product": {
     *                 "description": "Green Plate",
     *                 "ean": "1234567891234",
     *                 "image": "https://greenplate.dk/wp-content/uploads/2021/01/Green-Plate-Logo.png",
     *                 "category": [
     *                     "Food"
     *                 ]
     *             }
     *         }
     *     ]
     * }
     */

    /**
     * The list of clearances received in the response.
     */
    public List<Clearance> clearances;

    /**
     * Inner static class representing a clearance in the Salling Group's API response.
     */
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Clearance {
        /**
         * The details of the offer associated with the clearance.
         */
        public Offer offer;

        /**
         * The details of the product associated with the clearance.
         */
        public Product product;
    }

    /**
     * Inner static class representing offer details in the Salling Group's API response.
     */
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Offer {
        /**
         * The original price of the offer.
         */
        public double originalPrice;

        /**
         * The new price of the offer.
         */
        public double newPrice;

        /**
         * The discount amount.
         */
        public double discount;

        /**
         * The percentage discount.
         */
        public double percentDiscount;
    }

    /**
     * Inner static class representing product details in the Salling Group's API response.
     */
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Product {
        /**
         * The description of the product.
         */
        public String description;

        /**
         * The EAN (European Article Number) of the product.
         */
        public String ean;

        /**
         * The URL of the product image.
         */
        public String image;

        /**
         * The categories to which the product belongs.
         */
        public String[] category;
    }
}
