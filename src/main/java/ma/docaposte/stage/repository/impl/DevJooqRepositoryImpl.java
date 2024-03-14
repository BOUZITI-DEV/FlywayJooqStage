package ma.docaposte.stage.repository.impl;

import ma.docaposte.generated.tables.Dev;
import ma.docaposte.stage.dto.DevDTO;
import ma.docaposte.stage.repository.DevJooqRepository;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DevJooqRepositoryImpl implements DevJooqRepository {
    private final DSLContext dslContext;

    public DevJooqRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public void save(DevDTO devDTO) {
        dslContext.insertInto(Dev.DEV)
                .columns(Dev.DEV.NOM, Dev.DEV.PRENOM)
                .values(devDTO.getNom(), devDTO.getPrenom())
                .execute();
    }

    @Override
    public void update(DevDTO devDTO) {
        dslContext.update(Dev.DEV)
                .set(Dev.DEV.NOM, devDTO.getNom())
                .set(Dev.DEV.PRENOM, devDTO.getPrenom())
                .where(Dev.DEV.ID.eq(devDTO.getId()))
                .execute();
    }

    @Override
    public int delete(Long id) {
        return dslContext.deleteFrom(Dev.DEV)
                .where(Dev.DEV.ID.eq(id))
                .execute();
    }

    @Override
    public DevDTO findById(Long id) {
        return dslContext.selectFrom(Dev.DEV)
                .where(Dev.DEV.ID.eq(id))
                .fetchOneInto(DevDTO.class);
    }

    @Override
    public List<DevDTO> findAll() {
        return dslContext.selectFrom(Dev.DEV)
                .fetchInto(DevDTO.class);
    }

    @Override
    public Long count() {
        return (long) dslContext.fetchCount(Dev.DEV);
    }
}
