package view.supply_view;

public class ElevatorView {
    
    public void printElevatorTurnedOn(){
        System.out.println("The elevator is turned on");
    }

    public void printElevatorTurnedOff(){
        System.out.println("The elevator is turned off");
    }

    public void printNoMovementForCarToAnotherLevel(){
        System.out.println("The car can not be moved to another level");
    }

    public void printCarMovedToAnotherLevel(int levelNumber){
        System.out.println("The car was moved to level " + levelNumber);
    }
}
