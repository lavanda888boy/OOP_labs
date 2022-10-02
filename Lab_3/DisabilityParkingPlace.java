public class DisabilityParkingPlace extends ParkingPlace{

    public DisabilityParkingPlace(){
        super();
    }

    @Override
    public void occupy(Car car){
        super.occupy(car);
        car.getDriver().setPaymentState(true);
        System.out.println("A "+ this.getClass().toString() +" was occupied by the "+ car.getClass().toString() +" with id "+ car.getID());
    }
}