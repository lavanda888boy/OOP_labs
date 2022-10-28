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

        parking.getLevels().add(new Level(0, 15));
        parking.getLevels().add(new Level(1, 20));
        parking.getLevels().add(new Level(2, 20));

        parking.getServiceManager().open(parking);
        TimeUnit.SECONDS.sleep(2);
        System.out.println();



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

    private static void proceedCar(Car car, Parking parking) throws InterruptedException{
        parking.parkTheCar();
        System.out.println();
        TimeUnit.SECONDS.sleep(2);
        parking.removeTheCar(car.getID());
    }
}