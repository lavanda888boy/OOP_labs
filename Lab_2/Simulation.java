import java.util.Random;
import java.lang.StringBuilder;

public class Simulation{
    public static void main(String[] args) {
        
        Gate gate = new Gate();
        Elevator elevator = new Elevator(1500);
        PaymentTerminal paymentTerminal = new PaymentTerminal();
        ServiceManager serviceManager = new ServiceManager("Seva");
        CarQueue carQueue = new CarQueue();

        Parking parking = new Parking(gate, elevator, paymentTerminal, serviceManager, carQueue);

        parking.getLevels().add(new Level(0, 3));
        parking.getLevels().add(new Level(1, 2));

        serviceManager.openParking(parking);
        System.out.println();
        
        
        /*
        parking.getCarQueue().addCar(new Car("DTE 430", new Driver("Bob"), "usual", 1200));
        parking.getCarQueue().addCar(new Car("GHG 788", new Driver("Steve"), "usual", 1600));
        parking.getCarQueue().addCar(new Car("KLK 670", new Driver("Bill"), "usual", 1300));
        System.out.println();

        parking.parkTheCar();
        System.out.println();

        parking.parkTheCar();
        System.out.println();
        
        parking.removeTheCar("DTE 430");
        */

        Random r = new Random();
        int queue_size = r.nextInt(10) + 1;

        for(int i = 0; i < queue_size; i++) {
            String numberID = generateID();
            int mass = r.nextInt(2000) + 1000;
            parking.getCarQueue().addCar(new Car(numberID, new Driver("Steve"), "usual", mass));
        }

        System.out.println();

        while(!parking.getCarQueue().isEmptyOfCars()){
            parking.parkTheCar();
            System.out.println();
        }
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