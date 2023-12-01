package dk.kea.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SallingResponse    {
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
    public List<Clearance> clearances;

//    public String toString(){
//        return clearances.get(0).product.description;
//    }
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Clearance{
        public Offer offer;
        public Product product;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Offer{
        public double originalPrice;
        public double newPrice;
        public double discount;
        public double percentDiscount;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Product{
        public String description;
        public String ean;
        public String image;
        public String [] category;
    }
}
