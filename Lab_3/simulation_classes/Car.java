public class Car{

  private String id;
  private Driver driver;
  private int mass;

  public Car(String id, Driver driver, int mass){
    this.id = id;
    this.driver = driver;
    this.mass = mass;
  }

  public String getID(){
    return this.id;
  }

  public Driver getDriver(){
    return this.driver;
  }

  public int getMass(){
    return this.mass;
  }
}
