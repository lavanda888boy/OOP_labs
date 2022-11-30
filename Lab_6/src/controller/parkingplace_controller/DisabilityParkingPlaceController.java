package controller.parkingplace_controller;

import connection_interfaces.ParkingPlaceInterface;
import controller.driver_controller.DriverController;
import controller.vehicle_controller.DisabilityCarController;
import model.parkingplace_model.DisabilityParkingPlace;
import model.vehicle_model.Car;
import model.vehicle_model.DisabilityCar;
import view.driver_view.DriverView;
import view.vehicle_view.DisabilityCarView;

public class DisabilityParkingPlaceController implements ParkingPlaceInterface  {

    private DriverView driverView = new DriverView();
    private DriverController driverController = new DriverController(null, driverView); 

    private DisabilityCarView disabilityCarView = new DisabilityCarView();
    private DisabilityCarController disabilityCarController = new DisabilityCarController(null, disabilityCarView); 

    private DisabilityParkingPlace disabilityParkingPlace;

    public DisabilityParkingPlaceController(DisabilityParkingPlace disabilityParkingPlace){
        this.disabilityParkingPlace = disabilityParkingPlace;
    }

    @Override
    public void occupy(Car car){
        disabilityParkingPlace.setCar(car);
        disabilityParkingPlace.setParkingPlaceState(true);

        driverController.setDriver(car.getDriver());;
        driverController.setDriverPaymentState(false);

        DisabilityCar dc = (DisabilityCar) car;
        disabilityCarController.setDisabilityCar(dc);;

        disabilityCarController.openRamp();
        driverView.showDisabilityDriverLeftCar();
        disabilityCarController.closeRamp();
    }

    @Override
    public void free(){
        disabilityParkingPlace.setParkingPlaceState(false);
        
        disabilityCarController.openRamp();
        driverView.showDisabilityDriverEnteredCar();
        disabilityCarController.closeRamp();

        disabilityParkingPlace.setCar(null);
    }
}
