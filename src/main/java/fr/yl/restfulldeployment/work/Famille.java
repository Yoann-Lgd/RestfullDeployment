package fr.yl.restfulldeployment.work;

import fr.yl.restfulldeployment.dao.DAOFactory;

public class Famille {

    private int familleId;
    private String familleLibelle;
    private int classificationId;

    public Famille(int familleId, String familleLibelle, int classificationId) {
        this.familleId = familleId;
        this.familleLibelle = familleLibelle;
        this.classificationId = classificationId;
    }

    public void setFamilleId(int familleId) {
        this.familleId = familleId;
    }

    public int getFamilleId() {
        return familleId;
    }

    public String getFamilleLibelle() {
        return familleLibelle;
    }

    public void setFamilleLibelle(String familleLibelle) {
        this.familleLibelle = familleLibelle;
    }

    public int getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(int classificationId) {
        this.classificationId = classificationId;
    }

    public Classification getClassificationObject() {
        return DAOFactory.getClassificationDAO().getByID(classificationId);
    }

    public void setClassificationObject(Classification classification) {
        this.classificationId = classification.getClassificationId();
    }

    @Override
    public String toString() {
        return familleLibelle;
    }
}
