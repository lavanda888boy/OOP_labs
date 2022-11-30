package controller.parkingplace_controller;

import connection_interfaces.ParkingPlaceInterface;
import model.parkingplace_model.ElectricParkingPlace;
import model.vehicle_model.Car;
import model.vehicle_model.ElectricCar;
import view.parkingplace_view.ElectricParkingPlaceView;
import view.vehicle_view.ElectricCarView;

public class ElectricParkingPlaceController implements ParkingPlaceInterface  {

    ElectricCarView electricCarView = new ElectricCarView();
    
    private ElectricParkingPlace electricParkingPlace;
    private ElectricParkingPlaceView electricParkingPlaceView;

    public ElectricParkingPlaceController(ElectricParkingPlace electricParkingPlace,
                                            ElectricParkingPlaceView electricParkingPlaceView){
        this.electricParkingPlace = electricParkingPlace;
        this.electricParkingPlaceView = electricParkingPlaceView;
    }

    public void setParkingPlace(ElectricParkingPlace place){
        this.electricParkingPlace = place;
    }


    public void fillCharger(){
        electricParkingPlace.setCurrentVolume(electricParkingPlace.getChargerCapacity());
    }

    @Override
    public void occupy(Car car){
        electricParkingPlace.setCar(car);
        electricParkingPlace.setParkingPlaceState(true);
        chargeTheCar((ElectricCar) car);
    }

    @Override
    public void free(){
        electricParkingPlace.setCar(null);
        electricParkingPlace.setParkingPlaceState(false);
        electricParkingPlaceView.printElectricPlaceIsFree();
    }

    private void chargeTheCar(ElectricCar car){
        int bc = car.getBatteryCapacity();
        int bv = car.getBatteryVolume();

        if(bc == bv){
            electricCarView.printFullBattery();
            return;
        } else{
            if(electricParkingPlace.getCurrentVolume()  <  bc - bv){
                car.setBatteryVolume(bv + electricParkingPlace.getCurrentVolume() );
                electricParkingPlace.setCurrentVolume(0);
                electricCarView.printCarBatteryVolume((Math.round((double) (car.getBatteryVolume()) / (double) (car.getBatteryCapacity()) * 100)));
                electricParkingPlaceView.printChargerVolume(0);
            } else{
                electricParkingPlace.setCurrentVolume(electricParkingPlace.getCurrentVolume() - (bc - bv));
                car.setBatteryVolume(bc);
                electricCarView.printCarBatteryVolume(100);
                electricParkingPlaceView.printChargerVolume((Math.round((double) (electricParkingPlace.getCurrentVolume()) 
                                                            / (double) (electricParkingPlace.getChargerCapacity()) * 100)));
            }
        }
    }
}
