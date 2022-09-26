public class Elevator{

  private int maxWeight;
  private boolean workingState;

  public Elevator(int maxWeight){
    this.maxWeight = maxWeight;
    workingState = false;
  }

  public void lift(Level l, Car c){
    if(this.maxWeight < c.getMass()){
      System.out.println("The car can not be moved to another level");
    } else{
      System.out.println("The was moved to level " + l.getNumber());
    }
  }

  public void setWorkingState(boolean state){
    workingState = state;

    if(state == true){
      System.out.println("The elevator is turned on");
    } else{
      System.out.println("The elevator is turned off");
    }
  }
}
