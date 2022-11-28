package fr.yl.restfulldeployment.work;

import fr.kyo.crkf_web.dao.DAOFactory;

public class Departement {

    private final int departementId;
    private String departementNumero;
    private String departementLibelle;

    public Departement(int departementId, String departementNumero, String departementLibelle) {
        this.departementId = departementId;
        this.departementNumero = departementNumero;
        this.departementLibelle = departementLibelle;
    }

    public int getDepartementId() {
        return departementId;
    }

    public String getDepartementNumero() {
        return departementNumero;
    }

    public void setDepartementNumero(String departementNumero) {
        this.departementNumero = departementNumero;
    }

    public String getDepartementLibelle() {
        return departementLibelle;
    }

    public void setDepartementLibelle(String departementLibelle) {
        this.departementLibelle = departementLibelle;
    }

    @Override
    public String toString(){
        return departementId == 0 ? departementLibelle : departementNumero + " - " + departementLibelle;
    }
}
