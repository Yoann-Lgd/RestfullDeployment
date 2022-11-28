package fr.yl.restfulldeployment.work;

import fr.yl.restfulldeployment.dao.DAOFactory;
import java.util.ArrayList;
import java.util.List;

public class Instrument {
    private final int instrumentId;
    private String instrumentLibelle;
    private List<Integer> familles;

    public Instrument(int instrumentId, String instrumentLibelle) {
        this.instrumentId = instrumentId;
        this.instrumentLibelle = instrumentLibelle;
        familles = new ArrayList<>();
    }

    public Instrument() {
        instrumentId = 0;
        instrumentLibelle = "";
        familles = new ArrayList<>();
    }

    public int getInstrumentId() {
        return instrumentId;
    }

    public String getInstrumentLibelle() {
        return instrumentLibelle;
    }

    public void setInstrumentLibelle(String instrumentLibelle) {
        this.instrumentLibelle = instrumentLibelle;
    }

    public List<Famille> getFamillesObject() {
        List<Famille> list = new ArrayList<>();
        for(int i : familles)
            list.add(DAOFactory.getFamilleDAO().getByID(i));
        return list;
    }

    public List<Integer> getFamilles() {
        return familles;
    }

    public void setFamilles(List<Integer> familles) {
        this.familles = familles;
    }

    public void addFamille(Famille famille){
        familles.add(famille.getFamilleId());
    }

    @Override
    public String toString() {
        return instrumentLibelle;
    }
}
