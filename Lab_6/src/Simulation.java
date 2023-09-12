import java.util.Random;
import java.util.concurrent.TimeUnit;

import model.driver_model.*;
import model.vehicle_model.*;
import presenter.suppply_presenter.ParkingPresenter;
import repository.CarQueue;
import view.supply_view.ParkingView;
import view.vehicle_view.CarView;
import model.supply_model.*;

public class Simulation {

    public static final int levelCount = 3;
    public static final int levelCapacity = 10;

    public static final double simple_coef = 0.9;
    public static final double electric_coef = 0.05;
    public static final double disability_coef = 0.05;

    public static double incomePercent = 0.6;

    public static final int controlNumber = 1500;

    public static final int averageTime = 80;

    private static ParkingPresenter parkingPresenter;

    public static void main(String[] args) throws InterruptedException {
        String[] names = { "Dan", "Steve", "Peter", "Andy", "Matthew", "Paul", "Robert", "Angelo" };

        Gate gate = new Gate();
        Elevator elevator = new Elevator(1500);
        PaymentTerminal paymentTerminal = new PaymentTerminal(5, 15);
        CarQueue carQueue = new CarQueue();

        Parking parking = new Parking(gate, elevator, paymentTerminal, carQueue);
        ParkingView parkingView = new ParkingView();
        parkingPresenter = new ParkingPresenter(parking, parkingView);

        CarView carView = new CarView();

        for (int i = 0; i < levelCount; i++) {
            parking.getLevels().add(new Level(i, levelCapacity));
        }

        TimeUnit.SECONDS.sleep(2);
        System.out.println();

        fillTheParkingWithArbitraryCars(names, parking, levelCapacity / 2);

        Car newCar = generateRandomCar(names);
        parking.getCarQueue().addCar(newCar);

        Random r = new Random();
        int entranceTime;

        while (true) {

            validateCarMovement(parking);

            Car addableCar = generateRandomCar(names);
            Car leavingCar;

            entranceTime = r.nextInt(100) + 1;
            TimeUnit.MILLISECONDS.sleep(r.nextInt(controlNumber) + 2000);
            if (entranceTime <= incomePercent * 100) {
                parking.getCarQueue().addCar(addableCar);
                System.out.println("\n");
                if (parkingPresenter.parkTheCar() == 1) {
                    parking.getCarQueue().removeCar();
                }
                System.out.println("\n");
                carView.printCarWaiting(parking.getCarQueue().getFirstCar().getID());
                System.out.println();
            } else {
                if (parking.getCars().size() != 0) {
                    leavingCar = parkingPresenter.removeTheCar();
                    if (leavingCar instanceof ElectricCar) {
                        System.out.println("\n");
                        TimeUnit.MILLISECONDS.sleep(1000);
                        parkingPresenter.supplyTheChargers(parking.getLevels());
                        TimeUnit.MILLISECONDS.sleep(1000);
                    }
                    TimeUnit.MILLISECONDS.sleep(1000);
                    System.out.println("\n");
                    parkingPresenter.getPaymentTerminalView()
                            .printCurrentCashAmount(parking.getPaymentTerminal().getCashAmount());
                    System.out.println();
                }
            }

            System.out.println("\n");
            TimeUnit.MILLISECONDS.sleep(1000);
        }
    }

    private static void validateCarMovement(Parking parking) {
        if (parking.getCarQueue().getNumberOfCarsInTheQueue() > parking.getLevels().get(0).getCapacity()
                || parking.getCarQueue().getNumberOfCarsInTheQueue() < parking.getLevels().get(0).getCapacity() / 4) {
            incomePercent = 1 - incomePercent;
        }
    }

    private static String generateID(Random r) {
        StringBuilder sb = new StringBuilder();

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < 3; i++) {
            int index = r.nextInt(26);
            sb.append(alphabet.charAt(index));
        }
        sb.append(" ");

        int num = r.nextInt(900) + 100;
        sb.append(num);

        return sb.toString();
    }

    private static String generateName(Random r, String[] names) {
        String name = "";

        int index = r.nextInt(names.length);
        name = names[index];

        return name;
    }

    private static int generateTimeSpent(Random r) {
        return r.nextInt(averageTime) + 10;
    }

    private static int generateMass(Random r) {
        int mass = r.nextInt(1000) + 1000;
        return mass;
    }

    private static int generateCapacity(Random r) {
        return r.nextInt(2000) + 4000;
    }

    private static int generateVolume(Random r, int capacity) {
        return r.nextInt(capacity);
    }

    private static Car generateRandomCar(String[] names) {
        Random r = new Random();

        Car car;
        String id = generateID(r);
        String name = generateName(r, names);
        int time = generateTimeSpent(r);
        int mass = generateMass(r);

        int choice = r.nextInt(100) + 1;

        if (choice <= (int) (simple_coef * 100)) {
            car = new Car(id, new Driver(name, time), mass);
        } else if (choice > (int) (simple_coef * 100) && choice <= (int) (electric_coef * 100)) {
            int capacity = generateCapacity(r);
            car = new ElectricCar(id, new Driver(name, time), mass, capacity, generateVolume(r, capacity));
        } else {
            car = new DisabilityCar(id, new Driver(name, time), mass);
        }

        return car;
    }

    private static void fillTheParkingWithArbitraryCars(String[] names, Parking parking, int n)
            throws InterruptedException {
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

            parking.getCarQueue()
                    .addCar(new ElectricCar(generateID(r), d, generateMass(r), capacity, generateVolume(r, capacity)));
        }

        for (int i = 0; i < n * disability_coef; i++) {
            d = new Driver(generateName(r, names), generateTimeSpent(r));

            parking.getCarQueue().addCar(new DisabilityCar(generateID(r), d, generateMass(r)));
        }

        TimeUnit.SECONDS.sleep(2);

        System.out.println();
        while (!parking.getCarQueue().isEmptyOfCars()) {
            parkingPresenter.parkTheCar();
            parking.getCarQueue().removeCar();
            System.out.println();
        }
    }
}
