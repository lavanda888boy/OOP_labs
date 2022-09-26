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
    }
}
