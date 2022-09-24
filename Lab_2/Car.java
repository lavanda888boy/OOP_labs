public class Car{

  private Driver driver;
  private String category;
  private int mass;

  public Car(Driver driver, String category, int mass){
    this.driver = driver;
    this.category = category;
    this.mass = mass;
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
