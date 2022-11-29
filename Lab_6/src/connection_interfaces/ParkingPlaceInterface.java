package connection_interfaces;

import model.vehicle_model.Car;

public interface ParkingPlaceInterface {
    
    void occupy(Car car);

    void free();
}
