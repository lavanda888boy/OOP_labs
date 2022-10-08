public class Elevator{

  private int maxWeight;
  private boolean workingState;

  public Elevator(int maxWeight){
    this.maxWeight = maxWeight;
    workingState = false;
  }

  public boolean lift(Level l, Car c){
    if(this.maxWeight < c.getMass()){
      System.out.println("The car can not be moved to another level");
      return false;
    } else{
      System.out.println("The car was moved to level " + l.getNumber());
      return true;
    }
  }
  
  public boolean getWorkingState(){
    return this.workingState;
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
