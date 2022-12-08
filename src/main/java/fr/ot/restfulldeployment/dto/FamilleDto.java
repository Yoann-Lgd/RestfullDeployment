package fr.ot.restfulldeployment.dto;

import fr.ot.restfulldeployment.models.ClassificationEntity;
import fr.ot.restfulldeployment.models.FamilleEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FamilleDto {
    private int idFamille;
    private String famille;
    private Classification classification;

    public FamilleDto(FamilleEntity familleEntity) {
        this.idFamille = familleEntity.getIdFamille();
        this.famille = familleEntity.getFamille();
        this.classification = new Classification(familleEntity.getClassification());
    }

    public static List<FamilleDto> ListToFamilleDto(List<FamilleEntity> familleEntities){
        List<FamilleDto> familleDtos = new ArrayList<>();
        for(FamilleEntity familleEntity : familleEntities){
            familleDtos.add(new FamilleDto(familleEntity));
        }
        return familleDtos;
    }

    @Getter
    @Setter
    protected static class Classification{
        private int idClassification;
        private String classification;

        public Classification(ClassificationEntity classificationEntity) {
            this.idClassification = classificationEntity.getIdClassification();
            this.classification = classificationEntity.getClassification();
        }
    }
}
