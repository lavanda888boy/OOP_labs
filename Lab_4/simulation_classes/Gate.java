public class Gate implements WorkingStateProcessing{

  private boolean opened;

  public Gate(){
    this.opened = false;
  }

  public boolean getGateState(){
    return this.opened;
  }

  @Override
  public void open(){
    this.opened = true;
    System.out.println("The gate is opened");
  }

  @Override
  public void close(){
    this.opened = false;
    System.out.println("The gate is closed");
  }

}
