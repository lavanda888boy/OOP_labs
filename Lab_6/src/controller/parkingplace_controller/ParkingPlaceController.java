package controller.parkingplace_controller;

import connection_interfaces.ParkingPlaceInterface;
import model.parkingplace_model.ParkingPlace;
import model.vehicle_model.Car;
import view.parkingplace_view.ParkingPlaceView;

public class ParkingPlaceController implements ParkingPlaceInterface {
    
    private ParkingPlace parkingPlace;
    private ParkingPlaceView parkingPlaceView;

    public ParkingPlaceController(ParkingPlace parkingPlace, ParkingPlaceView parkingPlaceView){
        this.parkingPlace = parkingPlace;
        this.parkingPlaceView = parkingPlaceView;
    }

    @Override
    public void occupy(Car car){
        parkingPlace.setCar(car);
        parkingPlace.setParkingPlaceState(true);
        parkingPlaceView.printOccupyParkingPlace();
    }

    @Override
    public void free(){
        parkingPlace.setCar(null);
        parkingPlace.setParkingPlaceState(false);
        parkingPlaceView.printFreeParkingPlace();
    }
}
