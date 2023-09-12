package presenter.suppply_presenter;

import model.driver_model.Driver;
import model.supply_model.PaymentTerminal;
import presenter.driver_presenter.DriverPresenter;
import view.driver_view.DriverView;
import view.supply_view.PaymentTerminalView;

public class PaymentTerminalPresenter {

    private DriverView driverView = new DriverView();
    private DriverPresenter driverController = new DriverPresenter(null, driverView);

    private PaymentTerminal paymentTerminal;
    private PaymentTerminalView paymentTerminalView;

    public PaymentTerminalPresenter(PaymentTerminal paymentTerminal, PaymentTerminalView paymentTerminalView) {
        this.paymentTerminal = paymentTerminal;
        this.paymentTerminalView = paymentTerminalView;
    }

    public void setTerminalState(boolean state) {
        paymentTerminal.setWorkingState(state);

        if (state == true) {
            paymentTerminalView.printTerminalTurnedOn();
        } else {
            paymentTerminalView.printTerminalTurnedOff();
        }
    }

    public void proceedPayment(Driver d) {
        int time = d.getTimeSpent();

        if (time > 15 && time <= 60) {
            paymentTerminal.setCashAmount(paymentTerminal.getCashAmount() + paymentTerminal.getFirstLevelFee());
        } else if (time > 60) {
            paymentTerminal.setCashAmount(paymentTerminal.getCashAmount() + paymentTerminal.getSecondLevelFee());
        }

        driverController.setDriver(d);
        driverController.setDriverPaymentState(true);
        driverView.showDriverPaid(d.getName());
    }
}
