package controller.suppply_controller;

import model.supply_model.Elevator;
import model.supply_model.Level;
import model.vehicle_model.Car;
import view.supply_view.ElevatorView;

public class ElevatorController {

    private Elevator elevator;
    private ElevatorView elevatorView;

    public ElevatorController(Elevator elevator, ElevatorView elevatorView) {
        this.elevator = elevator;
        this.elevatorView = elevatorView;
    }

    public void setElevatorWorkingState(boolean workingState) {
        elevator.setWorkingState(workingState);

        if (workingState == true) {
            elevatorView.printElevatorTurnedOn();
        } else {
            elevatorView.printElevatorTurnedOff();
        }
    }

    public boolean liftCar(Level l, Car c) {
        if (elevator.getMaxWeight() < c.getMass()) {
            elevatorView.printNoMovementForCarToAnotherLevel();
            return false;
        } else {
            elevatorView.printCarMovedToAnotherLevel(l.getNumber());
            return true;
        }
    }
}
