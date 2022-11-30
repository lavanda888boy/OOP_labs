package controller.driver_controller;

import model.driver_model.Driver;
import view.driver_view.DriverView;

public class DriverController {

    private Driver driver;
    private DriverView driverView;

    public DriverController(Driver driver, DriverView driverView) {
        this.driver = driver;
        this.driverView = driverView;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setDriverPaymentState(boolean paymentState) {
        driver.setPaymentState(paymentState);
        driverView.showNoPaymentForDriver();
    }
}
