package controller.parkingplace_controller;

import connection_interfaces.ParkingPlaceInterface;
import model.parkingplace_model.DisabilityParkingPlace;
import model.vehicle_model.Car;
import view.parkingplace_view.DisabilityParkingPlaceView;

public class DisabilityParkingPlaceController implements ParkingPlaceInterface  {

    private DisabilityParkingPlace disabilityParkingPlace;
    private DisabilityParkingPlaceView disabilityParkingPlaceView;

    public DisabilityParkingPlaceController(DisabilityParkingPlace disabilityParkingPlace,
                                                DisabilityParkingPlaceView disabilityParkingPlaceView){
        this.disabilityParkingPlace = disabilityParkingPlace;
        this.disabilityParkingPlaceView = disabilityParkingPlaceView;
    }

    @Override
    public void occupy(Car car){

    }

    @Override
    public void free(){
        
    }
}
