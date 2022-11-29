package model.supply_model;

import java.util.ArrayList;
import java.util.List;

import model.parkingplace_model.DisabilityParkingPlace;
import model.parkingplace_model.ElectricParkingPlace;
import model.parkingplace_model.ParkingPlace;

public class Level {

    private final int electricity = 4000;

    private int number;
    private int capacity;
    private List<ParkingPlace> listOfParkingPlaces;

    public Level(int number, int capacity) {
        this.number = number;
        this.capacity = capacity;
        listOfParkingPlaces = new ArrayList<>();

        for (int i = 0; i < Simulation.simple_coef * capacity; i++) {
            ParkingPlace pp = new ParkingPlace();
            listOfParkingPlaces.add(pp);
        }

        for (int i = 0; i < Simulation.disability_coef * capacity; i++) {
            ParkingPlace dp = new DisabilityParkingPlace();
            listOfParkingPlaces.add(dp);
        }

        for (int i = 0; i < Simulation.electric_coef * capacity; i++) {
            ParkingPlace ep = new ElectricParkingPlace(electricity);
            listOfParkingPlaces.add(ep);
        }
    }

    public int getNumber() {
        return this.number;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public List<ParkingPlace> getListOfParkingPlaces() {
        return listOfParkingPlaces;
    }

    public boolean isFull() {
        for (int i = 0; i < listOfParkingPlaces.size(); i++) {
            if (!listOfParkingPlaces.get(i).getParkingPlaceState()) {
                return false;
            }
        }
        return true;
    }
}
