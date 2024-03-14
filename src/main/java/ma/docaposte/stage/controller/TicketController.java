package ma.docaposte.stage.controller;

import ma.docaposte.stage.dto.DevDTO;
import ma.docaposte.stage.dto.TicketDTO;
import ma.docaposte.stage.repository.impl.TicketJooqRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    private TicketJooqRepositoryImpl ticketJooqRepositoryImpl;

    @PostMapping("save")
    public ResponseEntity<Void> createTicket(@RequestBody TicketDTO ticketDTO) {
        ticketJooqRepositoryImpl.save(ticketDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("update")
    public ResponseEntity<Void> updateTicket(@RequestBody TicketDTO ticketDTO) {
        ticketJooqRepositoryImpl.update(ticketDTO);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        int rowsDeleted = ticketJooqRepositoryImpl.delete(id);
        if (rowsDeleted == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long id) {
        TicketDTO ticketDTO = ticketJooqRepositoryImpl.findById(id);
        if (ticketDTO != null) {
            return ResponseEntity.ok(ticketDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("findAll")
    public ResponseEntity<List<TicketDTO>> getAllDevs() {
        List<TicketDTO> TicketDTOs = ticketJooqRepositoryImpl.findAll();
        return ResponseEntity.ok(TicketDTOs);
    }
    @GetMapping("/count")
    public ResponseEntity<Long> countTickets() {
        Long count = ticketJooqRepositoryImpl.count();
        return ResponseEntity.ok(count);
    }
}
