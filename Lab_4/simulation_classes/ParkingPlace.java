abstract class ParkingPlace{

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

  abstract void occupy(Car car);

  abstract void free();

}
