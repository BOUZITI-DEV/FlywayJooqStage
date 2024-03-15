package ma.docaposte.stage.repository.impl;

import ma.docaposte.generated.tables.Author;
import ma.docaposte.stage.dto.AuthorDTO;
import ma.docaposte.stage.repository.AuthorJooqRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorJooqRepositoryImpl implements AuthorJooqRepository {
    private final DSLContext dslContext;

    public AuthorJooqRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public void save(AuthorDTO authorDTO) {
        dslContext.insertInto(Author.AUTHOR)
                .columns(Author.AUTHOR.NAME)
                .values(authorDTO.getName())
                .execute();
    }

    @Override
    public void update(AuthorDTO authorDTO) {

    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public AuthorDTO findById(Long id) {
        return null;
    }

    @Override
    public List<AuthorDTO> findAll() {
        return dslContext.selectFrom(Author.AUTHOR)
                .fetchInto(AuthorDTO.class);
    }

    @Override
    public Long count() {
        return null;
    }
}
