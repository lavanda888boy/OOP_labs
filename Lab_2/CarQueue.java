import java.util.Queue;
import java.util.LinkedList;

public class CarQueue{

  private Queue<Car> carQueue;

  public CarQueue(){
    carQueue = new LinkedList<>();
  }

  public void addCar(Car c){
    carQueue.add(c);
    System.out.println("The car with id " + c.getID() + " was added to the queue");
  }

  public Car removeCar(){
    Car car = carQueue.remove();
    System.out.println("The car with id " + car.getID() + " left the queue");
    return car;
  }

  public int getNumberOfCarsInTheQueue(){
    System.out.println("There are " + carQueue.size() + " cars in the queue now");
    return carQueue.size();
  }
}
