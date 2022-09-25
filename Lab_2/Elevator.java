public class Elevator{

  private int maxWeight;

  public Elevator(int maxWeight){
    this.maxWeight = maxWeight;
  }

  public void lift(Level l, Car c){
    if(this.maxWeight < c.getMass()){
      System.out.println("The car can not be moved to another level");
    } else{
      System.out.println("The was moved to level " + l.getNumber());
    }
  }
}
