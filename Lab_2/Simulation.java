public class Simulation{
    public static void main(String[] args) {
        
        Gate gate = new Gate();
        Elevator elevator = new Elevator(1500);
        PaymentTerminal paymentTerminal = new PaymentTerminal();
        ServiceManager serviceManager = new ServiceManager("Seva");
        CarQueue carQueue = new CarQueue();

        Parking parking = new Parking(gate, elevator, paymentTerminal, serviceManager, carQueue);
        parking.getCarQueue().addCar(new Car("DTE 430", new Driver("Bob"), "usual", 1200));
    }
}