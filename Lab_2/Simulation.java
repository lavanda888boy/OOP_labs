public class Simulation{
    public static void main(String[] args) {
        
        Gate gate = new Gate();
        Elevator elevator = new Elevator(1500);
        PaymentTerminal paymentTerminal = new PaymentTerminal();
        ServiceManager serviceManager = new ServiceManager("Seva");
        CarQueue carQueue = new CarQueue();

        Parking parking = new Parking(gate, elevator, paymentTerminal, serviceManager, carQueue);

        parking.getLevels().add(new Level(0, 2));
        parking.getLevels().add(new Level(1, 1));

        parking.getCarQueue().addCar(new Car("DTE 430", new Driver("Bob"), "usual", 1200));
        parking.getCarQueue().addCar(new Car("GHG 788", new Driver("Steve"), "usual", 1600));
        parking.getCarQueue().addCar(new Car("KLK 670", new Driver("Bill"), "usual", 1300));

        parking.parkTheCar();
    }
}