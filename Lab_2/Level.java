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

  public void isFull(){

  }
}
