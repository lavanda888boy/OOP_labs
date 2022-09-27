import java.util.Queue;
import java.util.LinkedList;

public class CarQueue{

  private Queue<Car> carQueue;

  public CarQueue(){
    carQueue = new LinkedList<>();
  }

  public void addCar(Car c){
    carQueue.add(c);
    System.out.println("The car with owner " + c.getDriver().getName() + " was added to the queue");
  }

  public Car removeCar(){
    Car car = carQueue.remove();
    System.out.println("The car with owner " + car.getDriver().getName() + " left the queue");
    return car;
  }

  public int getNumberOfCarsInTheQueue(){
    System.out.println("There are " + carQueue.size() + " cars in the queue now");
    return carQueue.size();
  }
}
