public class Car{

  private String id;
  private Driver driver;
  private String category;
  private int mass;

  public Car(String id, Driver driver, String category, int mass){
    this.id = id;
    this.driver = driver;
    this.category = category;
    this.mass = mass;
  }

  public String getID(){
    return this.id;
  }

  public Driver getDriver(){
    return this.driver;
  }

  public String getCategory(){
    return this.category;
  }

  public int getMass(){
    return this.mass;
  }
}
