package view.vehicle_view;

public class ElectricCarView {

    public void printFullBattery() {
        System.out.println("The car battery is full");
    }

    public void printCarBatteryVolume(double volume) {
        System.out.println("The car charged up to " + volume + "%");
    }
}
