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
    System.out.println("The "+car.getClass().toString().substring(6)+" with id "+car.getID()+" and mass "+car.getMass()+" kg and driver "+car.getDriver().getName()+" left the queue");
    return car;
  }

  public boolean isEmptyOfCars(){
    return carQueue.isEmpty();
  }

  public int getNumberOfCarsInTheQueue(){
    System.out.println("There are " + carQueue.size() + " cars in the queue now");
    return carQueue.size();
  }
}
