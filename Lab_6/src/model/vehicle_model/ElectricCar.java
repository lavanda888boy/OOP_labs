package model.vehicle_model;

import model.driver_model.Driver;

public class ElectricCar extends Car {
    
    private int batteryCapacity;
    private int batteryVolume;

    public ElectricCar(String id, Driver driver, int mass, int capacity, int volume){
        super(id, driver, mass);
        batteryCapacity = capacity;
        batteryVolume = volume;
    }

    public int getBatteryCapacity(){
        return this.batteryCapacity;
    }

    public int getBatteryVolume(){
        return this.batteryVolume;
    }

    public void setBatteryVolume(int v){
        batteryVolume = v;
    }
}
