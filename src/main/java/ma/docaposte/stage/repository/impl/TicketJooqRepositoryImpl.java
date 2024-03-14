package ma.docaposte.stage.repository.impl;

import ma.docaposte.generated.tables.Dev;
import ma.docaposte.generated.tables.Ticket;
import ma.docaposte.stage.dto.DevDTO;
import ma.docaposte.stage.dto.TicketDTO;
import ma.docaposte.stage.repository.TicketJooqRepository;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import org.jooq.Record;

import java.util.ArrayList;
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
        dslContext.update(Ticket.TICKET)
                .set(Ticket.TICKET.LIBELLE, ticketDTO.getLibelle())
                .set(Ticket.TICKET.DEV, ticketDTO.getDevDTO().getId())
                .where(Ticket.TICKET.ID.eq(Math.toIntExact(ticketDTO.getId())))
                .execute();
    }

    @Override
    public int delete(Long id) {
        return dslContext.deleteFrom(Ticket.TICKET)
                .where(Ticket.TICKET.ID.eq(Math.toIntExact(id)))
                .execute();
    }

    @Override
    public TicketDTO findById(Long id) {
        Record record = (Record) dslContext.select()
                .from(Ticket.TICKET)
                .join(Dev.DEV).on(Ticket.TICKET.DEV.eq(Dev.DEV.ID))
                .where(Ticket.TICKET.ID.eq(Math.toIntExact(id)))
                .fetchOne();

        if (record != null) {
            DevDTO devDTO = DevDTO.builder()
                    .id(record.get(Dev.DEV.ID))
                    .nom(record.get(Dev.DEV.NOM))
                    .prenom(record.get(Dev.DEV.PRENOM))
                    .build();

            TicketDTO ticketDTO = TicketDTO.builder()
                    .id(Long.valueOf(record.get(Ticket.TICKET.ID)))
                    .libelle(record.get(Ticket.TICKET.LIBELLE))
                    .devDTO(devDTO)
                    .build();

            System.out.println(ticketDTO);
            return ticketDTO;
        }
        return null;
    }

    @Override
    public List<TicketDTO> findAll() {
        List<TicketDTO> ticketDTOs = new ArrayList<>();

        Result<Record> records = dslContext.select()
                .from(Ticket.TICKET)
                .join(Dev.DEV).on(Ticket.TICKET.DEV.eq(Dev.DEV.ID))
                .fetch();

        for (Record record : records) {
            DevDTO devDTO = DevDTO.builder()
                    .id(record.get(Dev.DEV.ID))
                    .nom(record.get(Dev.DEV.NOM))
                    .prenom(record.get(Dev.DEV.PRENOM))
                    .build();

            TicketDTO ticketDTO = TicketDTO.builder()
                    .id(Long.valueOf(record.get(Ticket.TICKET.ID)))
                    .libelle(record.get(Ticket.TICKET.LIBELLE)) // Assuming you have a 'libelle' field
                    .devDTO(devDTO)
                    .build();

            ticketDTOs.add(ticketDTO);
        }

        return ticketDTOs;
    }


    @Override
    public Long count() {
        return (long) dslContext.fetchCount(Ticket.TICKET);
    }
}
