package ma.docaposte.stage.repository;

import ma.docaposte.stage.dto.BookDTO;

import java.util.List;

public interface BookJooqRepository {
    void save(BookDTO bookDTO);
    void update(BookDTO bookDTO);
    int delete(Long id);
    BookDTO findById(Long id);
    List<BookDTO> findAll();
    Long count();
}
