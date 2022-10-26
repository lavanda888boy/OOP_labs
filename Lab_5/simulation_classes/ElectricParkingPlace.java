import java.lang.Math;

public class ElectricParkingPlace extends ParkingPlace{

    private int currentVolume;
    private int chargerCapacity;

    public ElectricParkingPlace(int chargerCapacity){
        super();
        this.chargerCapacity = chargerCapacity;
        this.currentVolume = chargerCapacity;
    }

    public int getChargerCapacity(){
        return this.chargerCapacity;
    }

    public int getCurrentVolume(){
        return this.currentVolume;
    }

    public void fill(){
        this.currentVolume = this.chargerCapacity;
    }

    @Override
    public void occupy(Car car){
        this.setCar(car);
        this.setParkingPlaceState(true);
        chargeTheCar((ElectricCar) car);
    }

    @Override
    public void free(){
        this.setCar(null);
        this.setParkingPlaceState(false);
        System.out.println("An electric parking place is free now");
    }

    private void chargeTheCar(ElectricCar car){
        int bc = car.getBatteryCapacity();
        int bv = car.getBatteryVolume();

        if(bc == bv){
            System.out.println("The car battery is full");
            return;
        } else{
            if(this.currentVolume  <  bc - bv){
                car.setBatteryVolume(bv + this.currentVolume);
                this.currentVolume = 0;
                System.out.println("The car charged up to " + (Math.round((double) (car.getBatteryVolume()) / (double) (car.getBatteryCapacity()) * 100)) + "%");
                System.out.println("The charger volume is now 0%");
            } else{
                this.currentVolume -= (bc - bv);
                car.setBatteryVolume(bc);
                System.out.println("The car charged up to 100 %");
                System.out.println("The charger volume is now " + (Math.round((double) (this.currentVolume) / (double) (this.chargerCapacity) * 100)) + "%");
            }
        }
    }
}
