package ma.docaposte.stage.controller;

import ma.docaposte.stage.dto.DevDTO;
import ma.docaposte.stage.dto.TicketDTO;
import ma.docaposte.stage.repository.impl.TicketJooqRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    private TicketJooqRepositoryImpl ticketJooqRepositoryImpl;

    @PostMapping("save")
    public ResponseEntity<Void> createDev(@RequestBody TicketDTO ticketDTO) {
        ticketJooqRepositoryImpl.save(ticketDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getDevById(@PathVariable Long id) {
        TicketDTO ticketDTO = ticketJooqRepositoryImpl.findById(id);
        if (ticketDTO != null) {
            return ResponseEntity.ok(ticketDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
