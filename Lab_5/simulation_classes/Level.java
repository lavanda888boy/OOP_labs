import java.util.List;
import java.util.ArrayList;

public class Level{

  private final int electricity = 4000;

  private int number;
  private int capacity;
  private List<ParkingPlace> listOfParkingPlaces;

  public Level(int number, int capacity){
    this.number = number;
    this.capacity = capacity;
    listOfParkingPlaces = new ArrayList<>();

    for(int i = 0; i < Simulation.simple_coef * capacity; i++){
      ParkingPlace pp = new SimpleParkingPlace();
      listOfParkingPlaces.add(pp);
    }

    for(int i = 0; i < Simulation.disability_coef * capacity; i++){
      ParkingPlace dp = new DisabilityParkingPlace();
      listOfParkingPlaces.add(dp);
    }

    for(int i = 0; i < Simulation.electric_coef * capacity; i++){
      ParkingPlace ep = new ElectricParkingPlace(electricity);
      listOfParkingPlaces.add(ep);
    }
  }

  public int getNumber(){
    return this.number;
  }

  public int getCapacity(){
    return this.capacity;
  }

  public List<ParkingPlace> getListOfParkingPlaces(){
    return listOfParkingPlaces;
  }

  public boolean isFull(){
    for(int i = 0; i < listOfParkingPlaces.size(); i++){
      if(!listOfParkingPlaces.get(i).getParkingPlaceState()){
        System.out.println("There are available parking places on level " + getNumber());
        return false;
      }
    }
    System.out.println("There are no free parking places on level " + getNumber());
    return true;
  }

  public int getCarPosition(String id){
    for(int i = 0; i < listOfParkingPlaces.size(); i++){
      if(listOfParkingPlaces.get(i).getCar() != null  &&  listOfParkingPlaces.get(i).getCar().getID().compareTo(id) == 0){
        return i;
      }
    }
    return -1;
  }

  public void showAvailableParkingPlaces(){
    int simple_counter = 0;
    int electric_counter = 0;
    int disability_counter = 0;

    for(int i = (int) (Simulation.simple_coef * this.capacity) - 1; i >= 0; i--){
      if(listOfParkingPlaces.get(i).getParkingPlaceState()){
        break;
      }
      simple_counter++;
    }

    for(int i = this.capacity - 1; i > (int) ((1 - Simulation.electric_coef) * this.capacity); i--){
      if(listOfParkingPlaces.get(i).getParkingPlaceState()){
        break;
      }
      electric_counter++;
    }

    for(int i = (int) ((1 - Simulation.electric_coef) * this.capacity) - 1; i > (int) (Simulation.simple_coef * this.capacity); i--){
      if(listOfParkingPlaces.get(i).getParkingPlaceState()){
        break;
      }
      disability_counter++;
    }

    System.out.println("Free places on level " + this.getNumber() + ":");
    System.out.println(simple_counter + " for simple cars");
    System.out.println(electric_counter + " for electric cars");
    System.out.println(disability_counter + " for disability cars");
  }
  
}
