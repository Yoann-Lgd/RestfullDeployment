package fr.yl.restfulldeployment.work;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.yl.restfulldeployment.dao.DAOFactory;

public class Cycle {
    private final int cycleId;
    private String cycleLibelle;

    private int cycleNumero;

    public Cycle(int cycleId, String cycleLibelle, int cycleNumero) {
        this.cycleId = cycleId;
        this.cycleLibelle = cycleLibelle;
        this.cycleNumero = cycleNumero;
    }

    public int getCycleId() {
        return cycleId;
    }

    public String getCycleLibelle() {
        return cycleLibelle;
    }

    public void setCycleLibelle(String cycleLibelle) {
        this.cycleLibelle = cycleLibelle;
    }

    public int getCycleNumero() {
        return cycleNumero;
    }

    public void setCycleNumero(int cycleNumero) {
        this.cycleNumero = cycleNumero;
    }

    @Override
    public String toString() {
        return cycleLibelle;
    }
}
