import java.util.LinkedList;
import java.util.List;

public class Parking{

  private boolean workingState;

  private List<Level> levels;
  private Gate gate;
  private Elevator elevator;
  private PaymentTerminal paymentTerminal;
  private ServiceManager serviceManager;
  private CarQueue carQueue;

  public Parking(Gate g, Elevator el, PaymentTerminal pt, ServiceManager sm, CarQueue cq){
    workingState = false;
    levels = new LinkedList<>();
    gate = g;
    elevator = el;
    paymentTerminal = pt;
    serviceManager = sm;
    carQueue = cq;
  }

  public boolean getWorkingState(){
    return this.workingState;
  }

  public void open(){
    this.workingState = true;
    this.paymentTerminal.setWorkingState(true);
    this.elevator.setWorkingState(true);

    System.out.println("The parking is opened");
  }

  public void close(){
    this.workingState = false;
    this.paymentTerminal.setWorkingState(false);
    this.elevator.setWorkingState(false);

    System.out.println("The parking is closed");
  }

  public List<Level> getLevels(){
    return this.levels;
  }

  public Gate getGate(){
    return this.gate;
  }

  public Elevator getElevator(){
    return this.elevator;
  }

  public PaymentTerminal getPaymentTerminal(){
    return this.paymentTerminal;
  }

  public ServiceManager getServiceManager(){
    return this.serviceManager;
  }

  public CarQueue getCarQueue(){
    return this.carQueue;
  }

  public void parkTheCar(){
    Car c = this.carQueue.removeCar();

    for(int index = 0; index < this.levels.size(); index++){
      Level l = levels.get(index);
      if(!l.isFull()){
        ParkingPlace[] array = l.getArrayOfParkingPlaces();
        for(int i = 0; i < array.length; i++){
          if(!array[i].getParkingPlaceState()){
            array[i].occupy(c);
            System.out.println("The car with driver " + c.getDriver().getName() + " parked on the level " + l.getNumber());
            break;
          }
        }
      } else{
        if(index == levels.size() - 1){
          System.out.println("The car with driver " + c.getDriver().getName() + " can not be parked");
        }
        this.elevator.lift(levels.get(index + 1), c);
      }
    }
  }
}
