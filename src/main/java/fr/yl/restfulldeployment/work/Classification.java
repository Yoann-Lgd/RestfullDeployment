package fr.yl.restfulldeployment.work;

public class Classification {
    private int classificationId;
    private String classificationLibelle;

    public Classification(int classificationId, String classificationLibelle) {
        this.classificationId = classificationId;
        this.classificationLibelle = classificationLibelle;
    }

    public int getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(int classificationId) {
        this.classificationId = classificationId;
    }

    public String getClassificationLibelle() {
        return classificationLibelle;
    }

    public void setClassificationLibelle(String classificationLibelle) {
        this.classificationLibelle = classificationLibelle;
    }

    @Override
    public String toString() {
        return classificationLibelle;
    }
}
