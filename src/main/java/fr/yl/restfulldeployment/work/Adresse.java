package fr.yl.restfulldeployment.work;

import fr.kyo.crkf_web.dao.DAOFactory;

public class Adresse {

    private final int adresseId;
    private final String adresseLibelle;
    private int ville;

    public Adresse(int adresseId, String adresse, int ville) {
        this.adresseId = adresseId;
        this.adresseLibelle = adresse;
        this.ville = ville;
    }

    public int getAdresseId() {
        return adresseId;
    }

    public String getAdresseLibelle() {
        return adresseLibelle;
    }

    public Ville getVille() {
        return DAOFactory.getVilleDAO().getByID(ville);
    }

    public void setVille(Ville ville) {
        this.ville = ville.getVilleId();
    }

    public void setVille(int ville) {
        this.ville = ville;
    }

    @Override
    public String toString(){
        return adresseLibelle;
    }
}
