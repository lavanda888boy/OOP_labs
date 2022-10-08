public class Gate{

  private boolean opened;

  public Gate(){
    this.opened = false;
  }

  public boolean getGateState(){
    return this.opened;
  }

  public void open(){
    this.opened = true;
    System.out.println("The gate is opened");
  }

  public void close(){
    this.opened = false;
    System.out.println("The gate is closed");
  }

}
