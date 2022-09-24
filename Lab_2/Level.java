import java.util.List;
import java.util.ArrayList;

public class Level{

  private int capacity;
  private List<ParkingPlace> listOfParkingPlaces;

  public Level(int capacity){
    this.capacity = capacity;
    listOfParkingPlaces = new ArrayList<>();
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
