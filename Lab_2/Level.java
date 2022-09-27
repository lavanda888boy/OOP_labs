import java.util.List;
import java.util.ArrayList;

public class Level{

  private int number;
  private int capacity;
  private List<ParkingPlace> listOfParkingPlaces;

  public Level(int number, int capacity){
    this.number = number;
    this.capacity = capacity;
    listOfParkingPlaces = new ArrayList<>();

    for(int i = 0; i < capacity; i++){
      listOfParkingPlaces.add(new ParkingPlace());
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
      if(listOfParkingPlaces.get(i).getCar().getID().compareTo(id) == 0){
        return i;
      }
    }
    return -1;
  }
  
}
