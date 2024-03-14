package ma.docaposte.stage.controller;

import ma.docaposte.stage.dto.DevDTO;
import ma.docaposte.stage.repository.impl.DevJooqRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devs")
public class DevController {

    private final DevJooqRepositoryImpl devJooqRepositoryImpl;

    @Autowired
    public DevController(DevJooqRepositoryImpl devJooqRepositoryImpl) {
        this.devJooqRepositoryImpl = devJooqRepositoryImpl;
    }

    @PostMapping("save")
    public ResponseEntity<Void> createDev(@RequestBody DevDTO devDTO) {
        devJooqRepositoryImpl.save(devDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<Void> updateDev(@RequestBody DevDTO devDTO) {
        devJooqRepositoryImpl.update(devDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDev(@PathVariable Long id) {
        int rowsDeleted = devJooqRepositoryImpl.delete(id);
        if (rowsDeleted == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DevDTO> getDevById(@PathVariable Long id) {
        DevDTO devDTO = devJooqRepositoryImpl.findById(id);
        if (devDTO != null) {
            return ResponseEntity.ok(devDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("findAll")
    public ResponseEntity<List<DevDTO>> getAllDevs() {
        List<DevDTO> devDTOs = devJooqRepositoryImpl.findAll();
        return ResponseEntity.ok(devDTOs);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countDevs() {
        Long count = devJooqRepositoryImpl.count();
        return ResponseEntity.ok(count);
    }
}
