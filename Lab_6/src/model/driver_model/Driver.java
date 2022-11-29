package model.driver_model;

public class Driver{

    private String name;
    private int timeSpent;
    private boolean paymentState;
  
    public Driver(String name, int timeSpent){
      this.name = name;
      this.timeSpent = timeSpent;
      this.paymentState = false;
    }
  
    public String getName(){
      return this.name;
    }
  
    public int getTimeSpent(){
      return this.timeSpent;
    }
  
    public boolean getPaymentState(){
      return this.paymentState;
    }
  
    public void setPaymentState(boolean paymentState){
      this.paymentState = paymentState;
    }
  }
  
