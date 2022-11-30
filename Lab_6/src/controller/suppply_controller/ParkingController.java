package controller.suppply_controller;

import java.util.List;
import java.util.Random;

import connection_interfaces.WorkingStateInterface;
import controller.parkingplace_controller.*;
import controller.parkingplace_controller.ElectricParkingPlaceController;
import model.supply_model.*;
import model.vehicle_model.*;
import model.driver_model.Driver;
import model.parkingplace_model.*;
import view.parkingplace_view.*;
import view.supply_view.*;

public class ParkingController implements WorkingStateInterface {

    private PaymentTerminalView paymentTerminalView;
    private PaymentTerminalController paymentTerminalController;

    private ElevatorView elevatorView;
    private ElevatorController elevatorController;

    private GateView gateView;
    private GateController gateController;

    private ParkingPlaceView parkingPlaceView;
    private ParkingPlaceController parkingPlaceController;

    private ElectricParkingPlaceView electricParkingPlaceView;
    private ElectricParkingPlaceController electricParkingPlaceController;

    private DisabilityParkingPlaceController disabilityParkingPlaceController;

    private LevelView levelView;
    private LevelController levelController;

    private Parking parking;
    private ParkingView parkingView;

    public ParkingController(Parking parking, ParkingView parkingView) {
        this.parking = parking;
        this.parkingView = parkingView;

        paymentTerminalView = new PaymentTerminalView();
        paymentTerminalController = new PaymentTerminalController(parking.getPaymentTerminal(), paymentTerminalView);

        elevatorView = new ElevatorView();
        elevatorController = new ElevatorController(parking.getElevator(), elevatorView);

        gateView = new GateView();
        gateController = new GateController(parking.getGate(), gateView);

        parkingPlaceView = new ParkingPlaceView();
        parkingPlaceController = new ParkingPlaceController(null, parkingPlaceView);

        electricParkingPlaceView = new ElectricParkingPlaceView();
        electricParkingPlaceController = new ElectricParkingPlaceController(null, electricParkingPlaceView);

        disabilityParkingPlaceController = new DisabilityParkingPlaceController(null);

        levelView = new LevelView();
        levelController = new LevelController(null, levelView);
    }

    @Override
    public void open() {
        parking.setWorkingState(true);
        paymentTerminalController.setTerminalState(true);
        elevatorController.setElevatorWorkingState(true);
        parkingView.printParkingOpened();
    }

    @Override
    public void close() {
        parking.setWorkingState(false);
        paymentTerminalController.setTerminalState(false);
        elevatorController.setElevatorWorkingState(false);
        parkingView.printParkingClosed();
    }

    public int parkTheCar() {
        Car c = parking.getCarQueue().getFirstCar();

        List<Level> levels = parking.getLevels();

        for (int index = 0; index < levels.size(); index++) {
            Level l = levels.get(index);

            levelController.setLevel(l);

            if (levelController.levelIsFull()) {
                List<ParkingPlace> list = l.getListOfParkingPlaces();

                for (int i = 0; i < list.size(); i++) {
                    if (findThePlaceForTheCar(list, c) == 1) {
                        System.out.println("The " + c.getClass().toString().substring(6) + " with id " + c.getID()
                                + " is parked on the level " + l.getNumber());
                        parking.getCars().add(c);
                        gateController.close();
                        return 1;
                    }
                }

                if (index == levels.size() - 1) {
                    return 0;
                } else {
                    if (!elevatorController.liftCar(levels.get(index + 1), c)) {
                        break;
                    }
                    ;
                }
            }
        }
        return 0;
    }

    public Car removeTheCar() {
        Random r = new Random();

        int index = r.nextInt(parking.getCars().size());
        Car c = parking.getCars().get(index);
        String id = c.getID();

        List<Level> levels = parking.getLevels();

        for (Level level : levels) {
            int pos = levelController.getCarPositionOnLevel(id);

            if (pos != -1) {
                List<ParkingPlace> pp = level.getListOfParkingPlaces();

                Driver d = pp.get(pos).getCar().getDriver();
                if (!d.getPaymentState()) {
                    System.out.println("The driver " + d.getName() + " has to pay the bill");
                    paymentTerminalController.proceedPayment(d);
                }

                gateController.open();
                parking.getCars().remove(pp.get(pos).getCar());
                parkingPlaceController.setParkingPlace(pp.get(pos));
                parkingPlaceController.free();
                System.out.println("Car with id " + id + " left the parking from level " + level.getNumber());
                System.out.println("The driver spent on the parking " + c.getDriver().getTimeSpent() + " minutes");
                gateController.close();

                return c;
            }
        }
        System.out.println("There is no such car on the parking");
        return null;
    }

    private int findThePlaceForTheCar(List<ParkingPlace> places, Car car) {
        if (!(car instanceof ElectricCar) && !(car instanceof DisabilityCar)) {
            for (ParkingPlace p : places) {
                if (!(p instanceof ElectricParkingPlace) && !(p instanceof DisabilityParkingPlace)
                        && !p.getParkingPlaceState()) {
                    gateController.open();
                    parkingPlaceController.setParkingPlace(p);
                    parkingPlaceController.occupy(car);
                    return 1;
                }
            }
        } else if (car instanceof ElectricCar) {
            for (ParkingPlace p : places) {
                if (p instanceof ElectricParkingPlace && !p.getParkingPlaceState()) {
                    gateController.open();
                    electricParkingPlaceController.setParkingPlace((ElectricParkingPlace) p);
                    electricParkingPlaceController.occupy(car);
                    return 1;
                }
            }
        } else {
            for (ParkingPlace p : places) {
                if (p instanceof DisabilityParkingPlace && !p.getParkingPlaceState()) {
                    gateController.open();
                    disabilityParkingPlaceController.setParkingPlace((DisabilityParkingPlace) p);
                    disabilityParkingPlaceController.occupy(car);
                    return 1;
                }
            }
        }

        return 0;
    }
}
