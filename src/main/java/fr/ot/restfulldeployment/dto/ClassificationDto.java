package fr.ot.restfulldeployment.dto;

import fr.ot.restfulldeployment.models.ClassificationEntity;
import fr.ot.restfulldeployment.models.FamilleEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class ClassificationDto {
    private int idClassification;
    private String classification;
    private List<Famille> familles;

    public ClassificationDto(ClassificationEntity classificationEntity) {
        this.idClassification = classificationEntity.getIdClassification();
        this.classification = classificationEntity.getClassification();
        this.familles = ListToFamille(classificationEntity.getFamilles());
    }

    public static List<ClassificationDto> ListToClassificatioDto(List<ClassificationEntity> classificationEntities){
        List<ClassificationDto> classifications = new ArrayList<>();
        for(ClassificationEntity classificationEntity : classificationEntities){
            classifications.add(new ClassificationDto(classificationEntity));
        }
        return classifications;
    }

    private List<Famille> ListToFamille(List<FamilleEntity> familleEntities){
        List<Famille> familleList = new ArrayList<>();
        for(FamilleEntity familleEntity : familleEntities){
            familleList.add(new Famille(familleEntity));
        }
        return familleList;
    }
    @Getter
    @Setter
    protected class Famille{
        private int idFamille;
        private String famille;

        public Famille(FamilleEntity familleEntity) {
            this.idFamille = familleEntity.getIdFamille();
            this.famille = familleEntity.getFamille();
        }
    }
}
