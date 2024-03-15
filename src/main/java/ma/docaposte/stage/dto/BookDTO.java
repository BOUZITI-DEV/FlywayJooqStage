package ma.docaposte.stage.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookDTO {
    private Long id;
    private String title;
    List<AuthorDTO> authorDTOS;
}
