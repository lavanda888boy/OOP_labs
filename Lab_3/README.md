# MULTI-LEVEL PARKING SIMULATION (PART 2)

In this part of simulation development I added four additional classes: `DisabilityCar`,
`ElectricCar` (they extend `class Car`) and `DisabilityParkingPlace`, `ElectricParkingPlace`
(they extend `class ParkingPlace`).

`DisabilityParkingPlace` overrides the parent methods `occupy()` and `free()`, `ElectricParkingPlace`
overrides only the method `occupy()`.

All these four classes are bound together therefore I created supporting methods to handle and
distinguish them. Now my simulation provides additional functionality of charging the electric
on special parking places, helping the people with disabilities leave their cars.

The changes related to these classes were applied mainly to the `Parking` and `Level` classes.

Other members of the simulation remained the same with the dominating composition relationship
among them and also some aggregation. 

![alttext](https://github.com/lavanda888boy/OOP_labs_UTM/blob/main/Lab_3/class_diagram)
