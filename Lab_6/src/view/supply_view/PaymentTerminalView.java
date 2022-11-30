package view.supply_view;

public class PaymentTerminalView {

    public void printTerminalTurnedOn() {
        System.out.println("The payment terminal is turned on");
    }

    public void printTerminalTurnedOff() {
        System.out.println("The payment terminal is turned off");
    }

    public void printCurrentCashAmount(int amount) {
        System.out.println("Current cash amount is: " + amount + "$");
    }
}
