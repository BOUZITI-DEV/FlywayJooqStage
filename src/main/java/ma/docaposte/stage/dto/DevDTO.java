package ma.docaposte.stage.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DevDTO {
    private Long id;
    private String nom;
    private String prenom;
}
