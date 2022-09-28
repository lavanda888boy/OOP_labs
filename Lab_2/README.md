MULTI-LEVEL PARKING SIMULATION (PART 1)

The main class representing this model is the Parking class.
It contains the fields which are the instances of another classes
forming a composition of them. Those are List<Level> l, Elevator, Gate, CarQueue,
PaymentTerminal, ServiceManager. The Parking class itself contains 
a constructor, and void methods open(), close(), parkTheCar(), removeTheCar().

Its components have also a certain behavior. 

Elevator object can lift the cars to a certain level of the parking if their weight
is not bigger than its capacity.

PaymentTerminal changes the driver's payment state to demonstrate that he paid for parking.

Gate opens and closes to let the cars in or out.

CarQueue contains the cars which are waiting to be parked.

ServiceManager starts the parking's workday by switching on its components and opening the 
parking itself.

The list of levels stores the information about each Level object, which on its own stores 
data about the cars being parked on it and can search them by identification number (getCarPosition()). It also
has a certain capacity.

The classes Car and Driver are not directly connected to the Parking class but they are also important.
The Car object has information about its driver and possesses a certain id to be identified.

Finally, Simulation class has the main() method to simulate the parking model.
