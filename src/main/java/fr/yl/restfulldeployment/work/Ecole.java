package fr.yl.restfulldeployment.work;

import fr.yl.restfulldeployment.dao.DAOFactory;

public class Ecole {

    private final int ecoleId;
    private String ecoleNom;
    private final int ecoleAdresse;

    public Ecole(int ecoleId, String ecoleNom ,int ecoleAdresse) {
        this.ecoleId = ecoleId;
        this.ecoleNom = ecoleNom;
        this.ecoleAdresse = ecoleAdresse;
    }

    public int getEcoleId() {
        return ecoleId;
    }

    public Adresse getEcoleAdresse() {
        return DAOFactory.getAdresseDAO().getByID(ecoleAdresse);
    }

    public int getIdAdresse(){
        return ecoleAdresse;
    }

    public String getEcoleNom() {
        return ecoleNom;
    }
    public void setEcoleNom(String ecoleNom) {
        this.ecoleNom = ecoleNom;
    }

    @Override
    public String toString() {
        return ecoleNom;
    }
}
