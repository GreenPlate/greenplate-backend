package dk.kea.project.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCountResponse {
    String name;
    String ean;
    Long count;
}
