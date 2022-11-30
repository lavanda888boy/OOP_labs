package view.vehicle_view;

public class CarView {

    public void printCarLeftParking(String id, int number) {
        System.out.println("Car with id " + id + " left the parking from level " + number);
    }

    public void printCarWaiting(String id) {
        System.out.println("Car with id " + id + " is waiting");
    }

    public void printCarIsParked(String name, String id, int number) {
        System.out.println("The " + name + " with id " + id + " is parked on the level " + number);
    }
}
