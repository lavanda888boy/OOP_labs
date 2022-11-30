package controller.suppply_controller;

import controller.driver_controller.DriverController;
import model.driver_model.Driver;
import model.supply_model.PaymentTerminal;
import view.driver_view.DriverView;
import view.supply_view.PaymentTerminalView;

public class PaymentTerminalController {

    private DriverView driverView = new DriverView();
    private DriverController driverController = new DriverController(null, driverView);
    
    private PaymentTerminal paymentTerminal;
    private PaymentTerminalView paymentTerminalView;

    public PaymentTerminalController(PaymentTerminal paymentTerminal, PaymentTerminalView paymentTerminalView){
        this.paymentTerminal = paymentTerminal;
        this.paymentTerminalView = paymentTerminalView;
    }

    public void setTerminalState(boolean state){
        paymentTerminal.setWorkingState(state);

        if(state == true){
            paymentTerminalView.printTerminalTurnedOn();
        } else{
            paymentTerminalView.printTerminalTurnedOff();
        }
    }

    public void proceedPayment(Driver d){
        int time = d.getTimeSpent();
        
        if(time > 15  &&  time <= 60){
            paymentTerminal.setCashAmount(paymentTerminal.getCashAmount() + paymentTerminal.getFirstLevelFee());
        } else if(time > 60){
            paymentTerminal.setCashAmount(paymentTerminal.getCashAmount() + paymentTerminal.getSecondLevelFee());
        }

        driverController.setDriver(d);
        driverController.setDriverPaymentState(true);
        driverView.showDriverPaid(d.getName());
    }
}
