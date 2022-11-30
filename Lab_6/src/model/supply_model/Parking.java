package model.supply_model;

import java.util.LinkedList;
import java.util.List;

import model.vehicle_model.Car;
import repository.CarQueue;

public class Parking {

    private boolean workingState;

    private List<Level> levels;
    private List<Car> cars;
    private Gate gate;
    private Elevator elevator;
    private PaymentTerminal paymentTerminal;
    private CarQueue carQueue;

    public Parking(Gate g, Elevator el, PaymentTerminal pt, CarQueue cq) {
        workingState = false;
        levels = new LinkedList<>();
        cars = new LinkedList<>();
        gate = g;
        elevator = el;
        paymentTerminal = pt;
        carQueue = cq;
    }

    public boolean getWorkingState() {
        return this.workingState;
    }

    public void setWorkingState(boolean state){
        workingState = state;
    }

    public List<Level> getLevels() {
        return this.levels;
    }

    public List<Car> getCars() {
        return this.cars;
    }

    public Gate getGate() {
        return this.gate;
    }

    public Elevator getElevator() {
        return this.elevator;
    }

    public PaymentTerminal getPaymentTerminal() {
        return this.paymentTerminal;
    }

    public CarQueue getCarQueue() {
        return this.carQueue;
    }

    
}
