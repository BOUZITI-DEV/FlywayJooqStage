package ma.docaposte.stage.repository.impl;

import ma.docaposte.generated.tables.Dev;
import ma.docaposte.generated.tables.Ticket;
import ma.docaposte.stage.dto.TicketDTO;
import ma.docaposte.stage.repository.TicketJooqRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketJooqRepositoryImpl implements TicketJooqRepository {
    private final DSLContext dslContext;

    public TicketJooqRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public void save(TicketDTO ticketDTO) {
        dslContext.insertInto(Ticket.TICKET)
                .columns(Ticket.TICKET.LIBELLE, Ticket.TICKET.DEV)
                .values(ticketDTO.getLibelle(), ticketDTO.getDevDTO().getId())
                .execute();
    }

    @Override
    public void update(TicketDTO ticketDTO) {

    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public TicketDTO findById(Long id) {
        return null;
    }

    @Override
    public List<TicketDTO> findAll() {
        return null;
    }

    @Override
    public Long count() {
        return null;
    }
}
