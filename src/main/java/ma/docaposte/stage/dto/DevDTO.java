package ma.docaposte.stage.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DevDTO {
    private Long id;
    private String nom;
    private String prenom;
}
