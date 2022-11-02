import java.util.List;

public class ServiceManager{

    private String name;

    public ServiceManager(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void open(Parking p){
        if(p.getWorkingState() == false){
            System.out.println("SM " + this.name + " opens the parking");
            p.open();
        } else{
            System.out.println("The parking is already opened");
        }
    }

    public void close(Parking p){
        if(p.getWorkingState() == true){
            System.out.println("SM " + this.name + " closes the parking");
            p.close();
        } else{
            System.out.println("The parking is already closed");
        }
    }

    public void supplyTheChargers(List<Level> levels){
        for (Level level : levels) {
            List<ParkingPlace> places = level.getListOfParkingPlaces();
            for (ParkingPlace p : places) {
                if(p instanceof ElectricParkingPlace){
                    ElectricParkingPlace ep = (ElectricParkingPlace) p;
                    ep.fill();
                }
            }
        }
        System.out.println("SM " + this.name + " supplied the chargers");
    }
}
