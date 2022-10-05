import java.util.Random;
import java.lang.StringBuilder;
import java.util.concurrent.TimeUnit;

public class Simulation{
    public static void main(String[] args) throws InterruptedException {

        String[] names = {"Dan", "Steve", "Peter", "Andy", "Matthew", "Paul", "Robert", "Angelo"};
        
        Gate gate = new Gate();
        Elevator elevator = new Elevator(1500);
        PaymentTerminal paymentTerminal = new PaymentTerminal();
        ServiceManager serviceManager = new ServiceManager("Seva");
        CarQueue carQueue = new CarQueue();

        Parking parking = new Parking(gate, elevator, paymentTerminal, serviceManager, carQueue);

        parking.getLevels().add(new Level(0, 3));
        parking.getLevels().add(new Level(1, 2));

        parking.getServiceManager().openParking(parking);
        TimeUnit.SECONDS.sleep(2);
        System.out.println();
        
        
        Random r = new Random();

        for(int i = 0; i < 2; i++) {
            String numberID = generateID();
            int mass = r.nextInt(2000) + 1000;
            int name = r.nextInt(names.length);
            parking.getCarQueue().addCar(new Car(numberID, new Driver(names[name]), mass));
        }

        for(int i = 0; i < 2; i++) {
            String numberID = generateID();
            int mass = r.nextInt(2000) + 1000;
            int name = r.nextInt(names.length);
            parking.getCarQueue().addCar(new ElectricCar(numberID, new Driver(names[name]), mass, 4000, 1000));
        }

        for(int i = 0; i < 2; i++) {
            String numberID = generateID();
            int mass = r.nextInt(2000) + 1000;
            int name = r.nextInt(names.length);
            parking.getCarQueue().addCar(new DisabilityCar(numberID, new Driver(names[name]), mass));
        }

        System.out.println();

        while(!parking.getCarQueue().isEmptyOfCars()){
            TimeUnit.SECONDS.sleep(2);
            parking.parkTheCar();
            System.out.println();
        }

        TimeUnit.SECONDS.sleep(2);
        parking.getServiceManager().supplyTheChargers(parking.getLevels());
        System.out.println();

        TimeUnit.SECONDS.sleep(2);
        int carNumber = r.nextInt(parking.getCars().size() - 1);
        parking.removeTheCar(parking.getCars().get(carNumber).getID());
    }

    private static String generateID(){
        StringBuilder sb = new StringBuilder();
        Random r = new Random();

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i = 0; i < 3; i++){
            int index = r.nextInt(26);
            sb.append(alphabet.charAt(index));
        }
        sb.append(" ");

        int num = r.nextInt(900) + 100;
        sb.append(num);

        return sb.toString();
    }
}