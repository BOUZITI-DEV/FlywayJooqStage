package ma.docaposte.stage.repository.impl;

import ma.docaposte.generated.tables.Book;
import ma.docaposte.generated.tables.Dev;
import ma.docaposte.stage.dto.BookDTO;
import ma.docaposte.stage.dto.DevDTO;
import ma.docaposte.stage.repository.BookJooqRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookJooqRepositoryImpl implements BookJooqRepository {
    private final DSLContext dslContext;

    public BookJooqRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public void save(BookDTO bookDTO) {
        dslContext.insertInto(Book.BOOK)
                .columns(Book.BOOK.TITLE)
                .values(bookDTO.getTitle())
                .execute();
    }

    @Override
    public void update(BookDTO bookDTO) {

    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public BookDTO findById(Long id) {
        return null;
    }

    @Override
    public List<BookDTO> findAll() {
        return dslContext.selectFrom(Book.BOOK)
                .fetchInto(BookDTO.class);
    }

    @Override
    public Long count() {
        return null;
    }
}
