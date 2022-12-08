package fr.ot.restfulldeployment.repository;

import fr.ot.restfulldeployment.dto.ClassificationDto;
import fr.ot.restfulldeployment.models.ClassificationEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;

import java.util.List;

public class ClassificationRepository {
    private EntityManager entityManager = Persistence.createEntityManagerFactory("crkf").createEntityManager();

    @Transactional
    public List<ClassificationDto> getAll(){
        return ClassificationDto.ListToClassificatioDto(entityManager.createNamedQuery("classification.getAll", ClassificationEntity.class).getResultList());
    }

    @Transactional
    public ClassificationDto getById(int id){
        return new ClassificationDto(entityManager.find(ClassificationEntity.class, id));
    }
    @Transactional
    public boolean update(ClassificationEntity classification){
        ClassificationEntity updateEntity = entityManager.find(ClassificationEntity.class, classification.getIdClassification());
        if(updateEntity == null)return false;
        updateEntity.setClassification(classification.getClassification());
        entityManager.getTransaction().begin();
        entityManager.merge(updateEntity);
        entityManager.getTransaction().commit();
        return true;
    }

    @Transactional
    public int create(ClassificationEntity classification){
        if(classification == null)return 0;
        entityManager.getTransaction().begin();
        entityManager.persist(classification);
        entityManager.getTransaction().commit();
        return classification.getIdClassification();
    }

    @Transactional
    public boolean delete(int id){
        ClassificationEntity deleteObject = entityManager.find(ClassificationEntity.class, id);
        if(id == 0 || deleteObject == null)return false;
        entityManager.getTransaction().begin();
        entityManager.remove(deleteObject);
        entityManager.getTransaction().commit();
        return true;
    }

}
