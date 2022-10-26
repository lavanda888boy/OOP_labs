public class Driver{

  private String name;
  private boolean paymentState;

  public Driver(String name){
    this.name = name;
    this.paymentState = false;
  }

  public String getName(){
    return this.name;
  }

  public boolean getPaymentState(){
    return this.paymentState;
  }

  public void setPaymentState(boolean paymentState){
    this.paymentState = paymentState;
  }
}
