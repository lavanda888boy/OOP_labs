public class DisabilityParkingPlace extends ParkingPlace{

    public DisabilityParkingPlace(){
        super();
    }

    @Override
    public void occupy(Car car){
        super.occupy(car);
        car.getDriver().setPaymentState(true);
        System.out.println("The driver does not have to pay fee");

        DisabilityCar dc = (DisabilityCar) car;
        dc.openRamp();
        System.out.println("The driver with disabilities left the car");
        dc.closeRamp();
    }

    @Override
    public void free(){
        this.setParkingPlaceState(false);
        DisabilityCar dc = (DisabilityCar) this.getCar();
        dc.openRamp();
        System.out.println("The driver with disabilities got into the car");
        dc.closeRamp();

        this.setCar(null);
    }
}