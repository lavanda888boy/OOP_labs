package model.vehicle_model;

import model.driver_model.Driver;

public class DisabilityCar extends Car {
 
    private boolean rampState;

    public DisabilityCar(String id, Driver driver, int mass){
        super(id, driver, mass);
        this.rampState = false;
    }
}
