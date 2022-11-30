package model.supply_model;

public class Elevator {

    private int maxWeight;
    private boolean workingState;

    public Elevator(int maxWeight) {
        this.maxWeight = maxWeight;
        workingState = false;
    }

    public int getMaxWeight(){
        return this.maxWeight;
    }

    public boolean getWorkingState() {
        return this.workingState;
    }

    public void setWorkingState(boolean state) {
        workingState = state;
    }
}
