package ma.docaposte.stage.controller;

import ma.docaposte.stage.dto.AuthorDTO;
import ma.docaposte.stage.repository.impl.AuthorJooqRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {


    private final AuthorJooqRepositoryImpl authorJooqRepositoryImpl;

    public AuthorController(AuthorJooqRepositoryImpl authorJooqRepositoryImpl) {
        this.authorJooqRepositoryImpl = authorJooqRepositoryImpl;
    }

    @PostMapping("save")
    public ResponseEntity<Void> createDev(@RequestBody AuthorDTO bookDTO) {
        authorJooqRepositoryImpl.save(bookDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("findAll")
    public ResponseEntity<List<AuthorDTO>> getAllDevs() {
        List<AuthorDTO> AuthorDTOs = authorJooqRepositoryImpl.findAll();
        return ResponseEntity.ok(AuthorDTOs);
    }
}
