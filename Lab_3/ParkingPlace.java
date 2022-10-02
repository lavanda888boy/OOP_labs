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

  public void setCar(Car car){
    this.car = car;
  }

  public boolean getParkingPlaceState(){
    return this.occupied;
  }

  public void setParkingPlaceState(boolean state){
    this.occupied = state;
  }

  public void occupy(Car car){
    this.car = car;
    this.occupied = true;
  }

  public void free(){
    this.car = null;
    this.occupied = false;
    System.out.println("A parking place is free now");
  }

}
