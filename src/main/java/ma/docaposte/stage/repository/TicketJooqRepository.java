package ma.docaposte.stage.repository;

import ma.docaposte.stage.dto.TicketDTO;

import java.util.List;

public interface TicketJooqRepository {
    void save(TicketDTO ticketDTO);
    void update(TicketDTO ticketDTO);
    int delete(Long id);
    TicketDTO findById(Long id);
    List<TicketDTO> findAll();
    Long count();
}
