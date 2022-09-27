import java.util.List;
import java.util.ArrayList;

public class Level{

  private int number;
  private int capacity;
  private ParkingPlace[] arrayOfParkingPlaces;

  public Level(int number, int capacity){
    this.number = number;
    this.capacity = capacity;
    arrayOfParkingPlaces = new ParkingPlace[capacity];
  }

  public int getNumber(){
    return this.number;
  }

  public int getCapacity(){
    return this.capacity;
  }

  public ParkingPlace[] getArrayOfParkingPlaces(){
    return arrayOfParkingPlaces;
  }

  public boolean isFull(){
    for(int i = 0; i < arrayOfParkingPlaces.length; i++){
      if(!arrayOfParkingPlaces[i].getParkingPlaceState()){
        System.out.println("There are available parking places on level " + getNumber());
        return false;
      }
    }
    System.out.println("There no free parking places on level " + getNumber());
    return true;
  }
  
}
