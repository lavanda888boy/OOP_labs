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
        chargeTheCar(car);
    }

    private void chargeTheCar(Car car){
        int bc = car.getBatteryCapacity();
        int bv = car.getBatteryVolume();
        
        if(bc == bv){
            System.out.println("The car battery is full");
            return;
        } else{
            if(this.chargerCapacity  <  bc - bv){
                car.setBatteryVolume(this.chargerCapacity);
                this.chargerCapacity = 0;
                System.out.println("The car charged up to " + (double) (car.getBatteryVolume() / car.getBatteryCapacity() * 100) + "%");
            } else{
                car.setBatteryVolume(bc);
                this.chargerCapacity -= (bc - bv);
                System.out.println("The car charged up to 100 %");
            }
        }
    }
}