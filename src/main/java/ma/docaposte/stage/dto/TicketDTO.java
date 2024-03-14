package ma.docaposte.stage.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class TicketDTO {
    private Long id;
    private String libelle;
    private DevDTO devDTO;
}
