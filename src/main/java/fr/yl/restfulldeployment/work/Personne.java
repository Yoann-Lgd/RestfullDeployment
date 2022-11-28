package fr.yl.restfulldeployment.work;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.yl.restfulldeployment.dao.DAOFactory;
import java.util.ArrayList;
import java.util.List;

public class Personne {

    private int personneId;
    private String personneNom;
    private String personnePrenom;
    @JsonIgnore
    private int vehiculeCv;
    @JsonIgnore
    private int adresseId;
    @JsonIgnore
    private int ecoleID;
    @JsonIgnore
    private List<Diplome> diplomes;
    @JsonIgnore
    private List<Famille> familles;
    public Personne() {
        this.personneId = 0;
        this.personneNom = "";
        this.personnePrenom = "";
        diplomes = new ArrayList<>();
        familles = new ArrayList<>();
    }

    public Personne(int personneId, String nom, String prenom, int vehiculeCv, int adresse, int ecole) {
        this.personneId = personneId;
        this.personneNom = nom;
        this.personnePrenom = prenom;
        this.vehiculeCv = vehiculeCv;
        this.adresseId = adresse;
        this.ecoleID = ecole;
        diplomes = new ArrayList<>();
        familles = new ArrayList<>();
    }
    public int getPersonneId() {
        return personneId;
    }

    public void setPersonneId(int personneId) {
        this.personneId = personneId;
    }

    public String getPersonneNom() {
        return personneNom;
    }

    public void setPersonneNom(String personneNom) {
        this.personneNom = personneNom;
    }

    public String getPersonnePrenom() {
        return personnePrenom;
    }

    public void setPersonnePrenom(String personnePrenom) {
        this.personnePrenom = personnePrenom;
    }

    public int getVehiculeCv() {
        return vehiculeCv;
    }

    public void setVehiculeCv(int vehiculeCv) {
        this.vehiculeCv = vehiculeCv;
    }

    public int getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(int adresseId) {
        this.adresseId = adresseId;
    }

    public void setEcoleObject(int ecoleID) {
        this.ecoleID = ecoleID;
    }

    public Adresse getAdresseObject() {
        return DAOFactory.getAdresseDAO().getByID(adresseId);
    }

    public void setAdresseObject(Adresse adresseId) {
        this.adresseId = adresseId.getAdresseId();
    }

    public int getEcoleID() {
        return ecoleID;
    }

    public void setEcoleID(int ecoleID) {
        this.ecoleID = ecoleID;
    }

    public Ecole getEcoleObject() {
        return DAOFactory.getEcoleDAO().getByID(ecoleID);
    }

    public void setEcoleObject(Ecole ecoleID) {
        this.ecoleID = ecoleID.getEcoleId();
    }

    public List<Diplome> getDiplomes() {
        return diplomes;
    }
    public List<Famille> getFamilles() { return familles; }
    public void setFamilles(List<Famille> familles ) { this.familles = familles; }

    public void setDiplomes(List<Diplome> diplomes) {
        this.diplomes = diplomes;
    }

    public void addDiplome(Diplome diplome){
        diplomes.add(diplome);
    }

}
