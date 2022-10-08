import java.util.LinkedList;
import java.util.List;

public class Parking implements WorkingStateProcessing{

  private boolean workingState;

  private List<Level> levels;
  private List<Car> cars;
  private Gate gate;
  private Elevator elevator;
  private PaymentTerminal paymentTerminal;
  private ServiceManager serviceManager;
  private CarQueue carQueue;

  public Parking(Gate g, Elevator el, PaymentTerminal pt, ServiceManager sm, CarQueue cq){
    workingState = false;
    levels = new LinkedList<>();
    cars = new LinkedList<>();
    gate = g;
    elevator = el;
    paymentTerminal = pt;
    serviceManager = sm;
    carQueue = cq;
  }

  public boolean getWorkingState(){
    return this.workingState;
  }

  @Override
  public void open(){
    this.workingState = true;
    this.paymentTerminal.setWorkingState(true);
    this.elevator.setWorkingState(true);

    System.out.println("The parking is opened");
  }

  @Override
  public void close(){
    this.workingState = false;
    this.paymentTerminal.setWorkingState(false);
    this.elevator.setWorkingState(false);

    System.out.println("The parking is closed");
  }

  public List<Level> getLevels(){
    return this.levels;
  }

  public List<Car> getCars(){
    return this.cars;
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
            if(findThePlaceForTheCar(list, c) == 1){
              System.out.println("The "+ c.getClass().toString().substring(6) +" with id " + c.getID() + " is parked on the level " + l.getNumber());
              this.cars.add(c);
              this.gate.close();
              return;
            }
        }  
        System.out.println("The given car can not be parked on this level due to its type: " + c.getClass().toString().substring(6));   
        
        if(index == levels.size() - 1){
          System.out.println("The " + c.getClass().toString().substring(6) + " with id " + c.getID() + " can not be parked");
        } else{
          if(!this.elevator.lift(levels.get(index + 1), c)){
            break;
          };
        }       
      }
    }
  }


  private int findThePlaceForTheCar(List<ParkingPlace> places, Car car){
    if(!(car instanceof ElectricCar)  &&  !(car instanceof DisabilityCar)){
      for (ParkingPlace p : places) {
        if(!(p instanceof DisabilityParkingPlace)  &&  !(p instanceof ElectricParkingPlace)  &&  !p.getParkingPlaceState()){
          this.gate.open();
          p.occupy(car);
          return 1;
        }
      }
    } else if(car instanceof ElectricCar){
      for (ParkingPlace p : places) {
        if(p instanceof ElectricParkingPlace  &&  !p.getParkingPlaceState()){
          this.gate.open();
          p.occupy(car);
          return 1;
        }
      }
    } else{
      for (ParkingPlace p : places) {
        if(p instanceof DisabilityParkingPlace  &&  !p.getParkingPlaceState()){
          this.gate.open();
          p.occupy(car);
          return 1;
        }
      }
    }

    return 0;
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
        this.cars.remove(pp.get(pos).getCar());
        pp.get(pos).free();
        System.out.println("Car with id " + id + " left the parking from level " + level.getNumber());
        this.gate.close();

        return;
      }
    }
    System.out.println("There is no such car on the parking");
  }
}
