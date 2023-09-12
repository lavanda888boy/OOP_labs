package presenter.driver_presenter;

import model.driver_model.Driver;
import view.driver_view.DriverView;

public class DriverPresenter {

    private Driver driver;
    private DriverView driverView;

    public DriverPresenter(Driver driver, DriverView driverView) {
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
