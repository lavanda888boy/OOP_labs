import java.util.Random;
import java.lang.StringBuilder;
import java.util.concurrent.TimeUnit;


public class Simulation{

    public static final double simple_coef = 0.85;
    public static final double electric_coef = 0.1;
    public static final double disability_coef = 0.05;

    public static double incomePercent = 0.7;

    public static final int controlNumber = 1500;

    public static final int averageTime = 60;


    public static void main(String[] args) throws InterruptedException, IllegalArgumentException {

        String[] names = {"Dan", "Steve", "Peter", "Andy", "Matthew", "Paul", "Robert", "Angelo"};
        
        Gate gate = new Gate();
        Elevator elevator = new Elevator(1500);
        PaymentTerminal paymentTerminal = new PaymentTerminal(5, 15);
        ServiceManager serviceManager = new ServiceManager("Seva");
        CarQueue carQueue = new CarQueue();

        Parking parking = new Parking(gate, elevator, paymentTerminal, serviceManager, carQueue);

        parking.getLevels().add(new Level(0, 2));
        parking.getLevels().add(new Level(1, 2));
        parking.getLevels().add(new Level(2, 2));

        parking.getServiceManager().open(parking);
        TimeUnit.SECONDS.sleep(2);
        System.out.println();

        //int magicalNumber = 30;
        //fillTheParkingWithArbitraryCars(names, parking, magicalNumber);     
        
        Car newCar = generateRandomCar(names);
        parking.getCarQueue().addCar(newCar);

        Random r = new Random();
        int entranceTime;

        while(true){
            
            validateCarMovement(parking);

            Car addableCar = generateRandomCar(names);
            Car leavingCar;

            entranceTime = r.nextInt(100) + 1;
            TimeUnit.MILLISECONDS.sleep(r.nextInt(controlNumber) + 2000);
            if(entranceTime <= incomePercent * 100){
                parking.getCarQueue().addCar(addableCar);
                System.out.println("\n");
                if(parking.parkTheCar() == 1){
                    parking.getCarQueue().removeCar();
                }
            } else{
                if(parking.getCars().size() != 0){
                    leavingCar = parking.removeTheCar();
                    if(leavingCar instanceof ElectricCar){
                        System.out.println("\n");
                        TimeUnit.MILLISECONDS.sleep(1000);
                        parking.getServiceManager().supplyTheChargers(parking.getLevels());
                        TimeUnit.MILLISECONDS.sleep(1000);
                    }
                    TimeUnit.MILLISECONDS.sleep(1000);
                    System.out.println("Current cash amount is: "+parking.getPaymentTerminal().getCashAmount()+"$");
                    System.out.println();
                }
            }
            
            System.out.println("\n");
            TimeUnit.MILLISECONDS.sleep(1000);
        }
    }


    private static void validateCarMovement(Parking parking){
        if(parking.getCarQueue().getNumberOfCarsInTheQueue() > parking.getLevels().get(0).getCapacity()){
            incomePercent = 1 - incomePercent;
        }
    }

    
    private static String generateID(Random r){
        StringBuilder sb = new StringBuilder();

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


    private static String generateName(Random r, String[] names){
        String name = "";

        int index = r.nextInt(names.length);
        name = names[index];

        return name;
    }

    private static int generateTimeSpent(Random r){
        return r.nextInt(averageTime) + 10;
    }


    private static int generateMass(Random r){
        int mass = r.nextInt(1000) + 1000;
        return mass;
    }


    private static int generateCapacity(Random r){
        return r.nextInt(2000) + 4000;
    }


    private static int generateVolume(Random r, int capacity){
        return r.nextInt(capacity);
    }



    private static Car generateRandomCar(String[] names){
        Random r = new Random();

        Car car;
        String id = generateID(r);
        String name = generateName(r, names);
        int time = generateTimeSpent(r);
        int mass = generateMass(r);

        int choice = r.nextInt(100) + 1;

        if(choice <= (int) (simple_coef * 100)){
            car = new Car(id, new Driver(name, time), mass);
        } else if(choice > (int) (simple_coef * 100)  &&  choice <= (int) (electric_coef * 100)){
            int capacity = generateCapacity(r);
            car = new ElectricCar(id, new Driver(name, time), mass, capacity, generateVolume(r, capacity));
        } else{
            car = new DisabilityCar(id, new Driver(name, time), mass);
        }

        return car;
    }


    private static void fillTheParkingWithArbitraryCars(String[] names, Parking parking, int n) throws InterruptedException{
        Driver d;

        Random r = new Random();

        for (int i = 0; i < n * simple_coef; i++) {
            d = new Driver(generateName(r, names), generateTimeSpent(r));
            parking.getCarQueue().addCar(new Car(generateID(r), d, generateMass(r)));
        } 

        int capacity;
        for (int i = 0; i < n * electric_coef; i++) {
            d = new Driver(generateName(r, names), generateTimeSpent(r));
            capacity = generateCapacity(r);

            parking.getCarQueue().addCar(new ElectricCar(generateID(r), d, generateMass(r), capacity, generateVolume(r, capacity)));
        } 

        for (int i = 0; i < n * disability_coef; i++) {
            d = new Driver(generateName(r, names), generateTimeSpent(r));

            parking.getCarQueue().addCar(new DisabilityCar(generateID(r), d, generateMass(r)));
        } 

        TimeUnit.SECONDS.sleep(2);

        System.out.println();
        while(!parking.getCarQueue().isEmptyOfCars()){
            parking.parkTheCar();
            parking.getCarQueue().removeCar();
            System.out.println();
        }
    }
}