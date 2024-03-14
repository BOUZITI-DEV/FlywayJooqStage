package ma.docaposte.stage.repository;

import ma.docaposte.stage.dto.DevDTO;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface DevJooqRepository {
    void save(DevDTO devDTO);
    void update(DevDTO devDTO);
    int delete(Long id);
    DevDTO findById(Long id);
    List<DevDTO> findAll();
    Long count();
}
