package ma.docaposte.stage.controller;

import ma.docaposte.stage.dto.BookDTO;
import ma.docaposte.stage.repository.impl.BookJooqRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookJooqRepositoryImpl bookJooqRepositoryImpl;

    @PostMapping("save")
    public ResponseEntity<Void> createDev(@RequestBody BookDTO bookDTO) {
        bookJooqRepositoryImpl.save(bookDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("findAll")
    public ResponseEntity<List<BookDTO>> getAllDevs() {
        List<BookDTO> bookDTOs = bookJooqRepositoryImpl.findAll();
        return ResponseEntity.ok(bookDTOs);
    }
}
