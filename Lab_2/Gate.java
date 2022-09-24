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
  }

  public void close(){
    this.opened = false;
  }

}
