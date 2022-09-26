public class PaymentTerminal{

    public boolean workingState;

    public PaymentTerminal(){
        this.workingState = false;
    }

    public boolean getWorkingState(){
        return this.workingState;
    }

    public void setWorkingState(boolean state){
        workingState = state;

        if(state == true){
            System.out.println("The payment terminal is turned on");
        } else{
            System.out.println("The payment terminal is turned off");
        }
    }
}
