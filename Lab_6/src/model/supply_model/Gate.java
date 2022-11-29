package model.supply_model;

public class Gate {

    private boolean opened;

    public Gate() {
        this.opened = false;
    }

    public boolean getGateState() {
        return this.opened;
    }
}
