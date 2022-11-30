package model.supply_model;

public class PaymentTerminal {

    private boolean workingState;
    private int cashAmount;
    private int firstLevelFee;
    private int secondLevelFee;

    public PaymentTerminal(int first, int second) {
        this.workingState = false;
        this.cashAmount = 0;
        this.firstLevelFee = first;
        this.secondLevelFee = second;
    }

    public boolean getWorkingState() {
        return this.workingState;
    }

    public void setWorkingState(boolean state) {
        workingState = state;
    }

    public int getCashAmount() {
        return this.cashAmount;
    }

    public void setCashAmount(int cash) {
        cashAmount = cash;
    }

    public int getFirstLevelFee() {
        return this.firstLevelFee;
    }

    public int getSecondLevelFee() {
        return this.secondLevelFee;
    }
}
