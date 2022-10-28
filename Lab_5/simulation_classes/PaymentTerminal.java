public class PaymentTerminal{

    private boolean workingState;
    private int cashAmount;
    private int firtstLevelFee;
    private int secondLevelFee;


    public PaymentTerminal(int first, int second){
        this.workingState = false;
        this.cashAmount = 0;
        this.firtstLevelFee = first;
        this.secondLevelFee = second;
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

    public int getCashAmount(){
        return this.cashAmount;
    }

    public void proceedPayment(Driver d){
        int time = d.getTimeSpent();
        
        if(time > 15  &&  time <= 60){
            this.cashAmount += this.firtstLevelFee;
        } else if(time > 60){
            this.cashAmount += this.secondLevelFee;
        }

        d.setPaymentState(true);
        System.out.println("The driver " + d.getName() + " paid the fee");
    }
}
