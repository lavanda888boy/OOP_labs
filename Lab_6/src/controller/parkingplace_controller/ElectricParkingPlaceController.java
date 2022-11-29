package controller.parkingplace_controller;

import connection_interfaces.ParkingPlaceInterface;
import model.parkingplace_model.ElectricParkingPlace;
import model.vehicle_model.Car;
import view.parkingplace_view.ElectricParkingPlaceView;

public class ElectricParkingPlaceController implements ParkingPlaceInterface  {
    
    private ElectricParkingPlace electricParkingPlace;
    private ElectricParkingPlaceView electricParkingPlaceView;

    public ElectricParkingPlaceController(ElectricParkingPlace electricParkingPlace,
                                            ElectricParkingPlaceView electricParkingPlaceView){
        this.electricParkingPlace = electricParkingPlace;
        this.electricParkingPlaceView = electricParkingPlaceView;
    }

    // TODO: finish epp controller

    @Override
    public void occupy(Car car){

    }

    @Override
    public void free(){
        
    }
}
