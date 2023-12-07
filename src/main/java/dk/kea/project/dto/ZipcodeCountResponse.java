package dk.kea.project.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZipcodeCountResponse {
    String zipcode;
    Long count;
}
