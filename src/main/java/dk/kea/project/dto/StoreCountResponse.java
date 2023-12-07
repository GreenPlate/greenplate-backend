package dk.kea.project.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreCountResponse {
    String id;
    String name;
    Long count;
}
