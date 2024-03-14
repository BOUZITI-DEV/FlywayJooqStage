package ma.docaposte.stage.dto;

import lombok.*;

@Builder
@Data
public class TicketDTO {
    private Long id;
    private String libelle;
    private DevDTO devDTO;
}
