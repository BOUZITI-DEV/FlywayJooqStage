package ma.docaposte.stage.dto;

import lombok.*;

@Builder
@Data
public class DevDTO {
    private Long id;
    private String nom;
    private String prenom;
}
