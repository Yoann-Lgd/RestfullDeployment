package fr.ot.restfulldeployment.repository;

import fr.ot.restfulldeployment.dto.FamilleDto;
import fr.ot.restfulldeployment.models.FamilleEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;

import java.util.List;

public class FamilleRepository {
    private EntityManager entityManager = Persistence.createEntityManagerFactory("crkf").createEntityManager();

    @Transactional
    public List<FamilleDto> getAll(){
        return FamilleDto.ListToFamilleDto(entityManager.createNamedQuery("famille.getAll", FamilleEntity.class).getResultList());
    }

}
