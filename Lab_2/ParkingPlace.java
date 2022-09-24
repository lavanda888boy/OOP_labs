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
  }

  public void free(){
    this.car = null;
    this.occupied = false;
  }

}
