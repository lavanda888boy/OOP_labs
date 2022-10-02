public class ElectricParkingPlace extends ParkingPlace{

    private int chargerCapacity;

    public ElectricParkingPlace(int chargerCapacity){
        super();
        this.chargerCapacity = chargerCapacity;
    }

    public int getChargerCapacity(){
        return this.chargerCapacity;
    }

    @Override
    public void occupy(Car car){
        super.occupy(car);
        // method to charge the car 
    }
}