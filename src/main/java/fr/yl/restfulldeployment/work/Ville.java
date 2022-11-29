package fr.yl.restfulldeployment.work;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.yl.restfulldeployment.dao.DAOFactory;

public class Ville {

    private final int villeId;
    private String villeLibelle;

    private float longitude;

    private float latitude;

    private int departementId;

    public Ville(int villeId, String villeLibelle, float longitude, float latitude ,int departementId) {
        this.villeId = villeId;
        this.villeLibelle = villeLibelle;
        this.longitude = longitude;
        this.latitude = latitude;
        this.departementId = departementId;
    }

    public int getVilleId() {
        return villeId;
    }

    public String getVilleLibelle() {
        return villeLibelle;
    }

    public void setVilleLibelle(String villeLibelle) {
        this.villeLibelle = villeLibelle;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public Departement getDepartement() {
        return DAOFactory.getDepartementDAO().getByID(departementId);
    }

    public void setDepartementId(Departement departementId) {
        this.departementId = departementId.getDepartementId();
    }

    public void setDepartement(int departement) {
        this.departementId = departement;
    }

    @Override
    public String toString(){
        return villeLibelle;
    }

}
