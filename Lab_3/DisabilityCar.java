public class DisabilityCar extends Car{

    private boolean rampState;

    public DisabilityCar(String id, Driver driver, int mass){
        super(id, driver, mass);
        this.rampState = false;
    }

    public void openRamp(){
        this.rampState = true;
        System.out.println("The ramp of the car with id "+ this.getID() +" is opened");
    }

    public void closeRamp(){
        this.rampState = false;
        System.out.println("The ramp of the car with id "+ this.getID() +" is closed");
    }
}