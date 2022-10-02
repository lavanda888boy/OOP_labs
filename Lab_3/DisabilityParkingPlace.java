public class DisabilityParkingPlace extends ParkingPlace{

    public DisabilityParkingPlace(){
        super();
    }

    @Override
    public void occupy(Car car){
        super.occupy(car);
        car.getDriver().setPaymentState(true);
        System.out.println("The driver does not have to pay fee");
    }
}