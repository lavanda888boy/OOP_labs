package view.supply_view;

public class LevelView {

    public void printNoAvailablePlacesOnLevel(int number) {
        System.out.println("There are no available parking places on level " + number);
    }

    public void printAvailablePlacesOnLevel(int number) {
        System.out.println("There are no available parking places on level " + number);
    }

    public void printFreeParkingPlaces(int number, int simple_counter, int electric_counter, int disability_counter) {
        System.out.println("Free places on level " + number + ":");
        System.out.println(simple_counter + " for simple cars");
        System.out.println(electric_counter + " for electric cars");
        System.out.println(disability_counter + " for disability cars");
    }
}
