package presenter.suppply_presenter;

import java.util.List;
import java.util.Random;

import connection_interfaces.WorkingStateInterface;
import model.supply_model.*;
import model.vehicle_model.*;
import presenter.parkingplace_presenter.DisabilityParkingPlacePresenter;
import presenter.parkingplace_presenter.ElectricParkingPlacePresenter;
import presenter.parkingplace_presenter.ParkingPlacePresenter;
import model.driver_model.Driver;
import model.parkingplace_model.*;
import view.parkingplace_view.*;
import view.driver_view.*;
import view.vehicle_view.*;
import view.supply_view.*;

public class ParkingPresenter implements WorkingStateInterface {

    private PaymentTerminalView paymentTerminalView;
    private PaymentTerminalPresenter paymentTerminalController;

    private ElevatorView elevatorView;
    private ElevatorPresenter elevatorController;

    private GateView gateView;
    private GatePresenter gateController;

    private ParkingPlaceView parkingPlaceView;
    private ParkingPlacePresenter parkingPlaceController;

    private ElectricParkingPlaceView electricParkingPlaceView;
    private ElectricParkingPlacePresenter electricParkingPlaceController;

    private DisabilityParkingPlacePresenter disabilityParkingPlaceController;

    private LevelView levelView;
    private LevelPresenter levelController;

    private CarView carView;
    private DriverView driverView;

    private Parking parking;
    private ParkingView parkingView;

    public ParkingPresenter(Parking parking, ParkingView parkingView) {
        this.parking = parking;
        this.parkingView = parkingView;

        paymentTerminalView = new PaymentTerminalView();
        paymentTerminalController = new PaymentTerminalPresenter(parking.getPaymentTerminal(), paymentTerminalView);

        elevatorView = new ElevatorView();
        elevatorController = new ElevatorPresenter(parking.getElevator(), elevatorView);

        gateView = new GateView();
        gateController = new GatePresenter(parking.getGate(), gateView);

        parkingPlaceView = new ParkingPlaceView();
        parkingPlaceController = new ParkingPlacePresenter(null, parkingPlaceView);

        electricParkingPlaceView = new ElectricParkingPlaceView();
        electricParkingPlaceController = new ElectricParkingPlacePresenter(null, electricParkingPlaceView);

        disabilityParkingPlaceController = new DisabilityParkingPlacePresenter(null);

        levelView = new LevelView();
        levelController = new LevelPresenter(null, levelView);

        carView = new CarView();

        driverView = new DriverView();
    }

    public PaymentTerminalView getPaymentTerminalView() {
        return this.paymentTerminalView;
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

            if (!levelController.levelIsFull()) {
                List<ParkingPlace> list = l.getListOfParkingPlaces();

                for (int i = 0; i < list.size(); i++) {
                    if (findThePlaceForTheCar(list, c) == 1) {
                        carView.printCarIsParked(c.getClass().toString(), c.getID(), l.getNumber());
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
                    driverView.showNeedToPayBill(d.getName());
                    paymentTerminalController.proceedPayment(d);
                }

                gateController.open();
                parking.getCars().remove(pp.get(pos).getCar());
                parkingPlaceController.setParkingPlace(pp.get(pos));
                parkingPlaceController.free();

                carView.printCarLeftParking(id, level.getNumber());
                driverView.showTimeSpentByDriver(c.getDriver().getTimeSpent());
                gateController.close();

                return c;
            }
        }
        parkingView.printNoSuchCar();
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

    public void supplyTheChargers(List<Level> levels) {
        for (Level level : levels) {
            List<ParkingPlace> places = level.getListOfParkingPlaces();
            for (ParkingPlace p : places) {
                if (p instanceof ElectricParkingPlace) {
                    ElectricParkingPlace ep = (ElectricParkingPlace) p;
                    electricParkingPlaceController.setParkingPlace(ep);
                    electricParkingPlaceController.fillCharger();
                }
            }
        }
        parkingView.printChargersSupplied();
    }
}
