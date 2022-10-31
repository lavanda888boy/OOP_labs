import java.util.Random;
import java.lang.StringBuilder;
import java.util.concurrent.TimeUnit;

public class Simulation{
    public static void main(String[] args) throws InterruptedException, IllegalArgumentException {

        String[] names = {"Dan", "Steve", "Peter", "Andy", "Matthew", "Paul", "Robert", "Angelo"};
        
        Gate gate = new Gate();
        Elevator elevator = new Elevator(1500);
        PaymentTerminal paymentTerminal = new PaymentTerminal(5, 15);
        ServiceManager serviceManager = new ServiceManager("Seva");
        CarQueue carQueue = new CarQueue();

        Parking parking = new Parking(gate, elevator, paymentTerminal, serviceManager, carQueue);

        parking.getLevels().add(new Level(0, 20));
        parking.getLevels().add(new Level(1, 20));
        parking.getLevels().add(new Level(2, 20));

        parking.getServiceManager().open(parking);
        TimeUnit.SECONDS.sleep(2);
        System.out.println();

        int magicalNumber = 30;
        fillTheParkingWithArbitraryCars(names, parking, magicalNumber);     
        
        Level l = parking.getLevels().get(0);
        l.showAvailableParkingPlaces();
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

    private static void fillTheParkingWithArbitraryCars(String[] names, Parking parking, int n) throws InterruptedException{
        String id;
        Driver d;
        int mass;

        Random r_name = new Random();
        Random r_mass = new Random();
        for (int i = 0; i < n * 0.85; i++) {
            id = generateID();
            d = new Driver(names[r_name.nextInt(names.length)]);
            mass = r_mass.nextInt(1000) + 1000;

            parking.getCarQueue().addCar(new Car(id, d, mass));
        } 

        int capacity, volume;

        Random r_cv = new Random();
        for (int i = 0; i < n * 0.1; i++) {
            id = generateID();
            d = new Driver(names[r_name.nextInt(names.length)]);
            mass = r_mass.nextInt(1000) + 1000;
            capacity = r_cv.nextInt(2000) + 4000;
            volume = r_cv.nextInt(capacity);
            parking.getCarQueue().addCar(new ElectricCar(id, d, mass, capacity, volume));
        } 

        for (int i = 0; i < n * 0.05; i++) {
            id = generateID();
            d = new Driver(names[r_name.nextInt(names.length)]);
            mass = r_mass.nextInt(1000) + 1000;
            parking.getCarQueue().addCar(new DisabilityCar(id, d, mass));
        } 

        TimeUnit.SECONDS.sleep(2);

        System.out.println();
        while(!parking.getCarQueue().isEmptyOfCars()){
            parking.parkTheCar();
            System.out.println();
        }
    }
}