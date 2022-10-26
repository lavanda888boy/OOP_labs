public class SimpleParkingPlace extends ParkingPlace{

    public SimpleParkingPlace(){
        super();
    }

    @Override
    public void occupy(Car car){
        this.setCar(car);
        this.setParkingPlaceState(true);
    }
    
    @Override
    public void free(){
        this.setCar(null);
        this.setParkingPlaceState(false);
        System.out.println("A parking place is free now");
    }
}