package view.driver_view;

public class DriverView {

    public void showNoPaymentForDriver() {
        System.out.println("The driver does not have to pay fee");
    }

    public void showNeedToPayBill(String name) {
        System.out.println("The driver " + name + " has to pay the bill");
    }

    public void showDriverPaid(String name) {
        System.out.println("The driver " + name + " paid the fee");
    }

    public void showDisabilityDriverLeftCar() {
        System.out.println("The driver with disabilities left the car");
    }

    public void showDisabilityDriverEnteredCar() {
        System.out.println("The driver with disabilities got into the car");
    }

    public void showTimeSpentByDriver(int time) {
        System.out.println("The driver spent on the parking " + time + " minutes");
    }
}
