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
        List<ParkingPlace> list = l.getListOfParkingPlaces();
        for(int i = 0; i < list.size(); i++){
          if(!list.get(i).getParkingPlaceState()){
            this.gate.open();
            list.get(i).occupy(c);
            System.out.println("The car with id " + c.getID() + " parked on the level " + l.getNumber());
            this.gate.close();
            return;
          }
        }
      } else{
        if(index == levels.size() - 1){
          System.out.println("The car with id " + c.getID() + " can not be parked");
        } else{
          this.elevator.lift(levels.get(index + 1), c);
        }
      }
    }
  }

  public void removeTheCar(String id){
    for(Level level : levels){
      int pos = level.getCarPosition(id);
      if(pos != -1){
        List<ParkingPlace> pp = level.getListOfParkingPlaces();

        Driver d = pp.get(pos).getCar().getDriver();
        if(!d.getPaymentState()){
          System.out.println("The driver " + d.getName() + " has to pay the bill");
          this.paymentTerminal.proceedPayment(d);
        }

        this.gate.open();
        pp.get(pos).free();
        System.out.println("Car with id " + id + " left the parking from level " + level.getNumber());
        this.gate.close();
        return;
      }
    }
    System.out.println("There is no such car on the parking");
  }
}
