package fr.yl.restfulldeployment.work;

import fr.yl.restfulldeployment.dao.DAOFactory;

public class Diplome {

    private int cycle;
    private int instrument;

    public Diplome(int cycle, int instrument) {
        this.cycle = cycle;
        this.instrument = instrument;
    }

    public Cycle getCycle() {
        return DAOFactory.getCycleDAO().getByID(cycle);
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle.getCycleId();
    }

    public Instrument getInstrument() {
        return DAOFactory.getInstrumentDAO().getByID(instrument);
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument.getInstrumentId();
    }
}
