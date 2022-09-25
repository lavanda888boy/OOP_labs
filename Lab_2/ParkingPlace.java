public class ParkingPlace{

  private Car car;
  private boolean occupied;

  public ParkingPlace(){
    this.car = null;
    this.occupied = false;
  }

  public Car getCar(){
    return this.car;
  }

  public boolean getParkingPlaceState(){
    return this.occupied;
  }

  public void occupy(Car car){
    this.car = car;
    this.occupied = true;
    System.out.println("A parking place was occupied by the car with owner " + c.getDriver().getName());
  }

  public void free(){
    this.car = null;
    this.occupied = false;
    System.out.println("A parking place is free now");
  }

}
