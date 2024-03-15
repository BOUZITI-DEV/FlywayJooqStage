package ma.docaposte.stage.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AuthorDTO {
    private Long id;
    private String name;
    private List<BookDTO> bookDTOS;
}
