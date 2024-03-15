package ma.docaposte.stage.repository;

import ma.docaposte.stage.dto.AuthorDTO;

import java.util.List;

public interface AuthorJooqRepository {
    void save(AuthorDTO authorDTO);
    void update(AuthorDTO authorDTO);
    int delete(Long id);
    AuthorDTO findById(Long id);
    List<AuthorDTO> findAll();
    Long count();
}
