package presenter.parkingplace_presenter;

import connection_interfaces.ParkingPlaceInterface;
import model.parkingplace_model.DisabilityParkingPlace;
import model.vehicle_model.Car;
import model.vehicle_model.DisabilityCar;
import presenter.driver_presenter.DriverPresenter;
import presenter.vehicle_presenter.DisabilityCarPresenter;
import view.driver_view.DriverView;
import view.vehicle_view.DisabilityCarView;

public class DisabilityParkingPlacePresenter implements ParkingPlaceInterface {

    private DriverView driverView = new DriverView();
    private DriverPresenter driverController = new DriverPresenter(null, driverView);

    private DisabilityCarView disabilityCarView = new DisabilityCarView();
    private DisabilityCarPresenter disabilityCarController = new DisabilityCarPresenter(null, disabilityCarView);

    private DisabilityParkingPlace disabilityParkingPlace;

    public DisabilityParkingPlacePresenter(DisabilityParkingPlace disabilityParkingPlace) {
        this.disabilityParkingPlace = disabilityParkingPlace;
    }

    public void setParkingPlace(DisabilityParkingPlace place) {
        this.disabilityParkingPlace = place;
    }

    @Override
    public void occupy(Car car) {
        disabilityParkingPlace.setCar(car);
        disabilityParkingPlace.setParkingPlaceState(true);

        driverController.setDriver(car.getDriver());
        ;
        driverController.setDriverPaymentState(false);

        DisabilityCar dc = (DisabilityCar) car;
        disabilityCarController.setDisabilityCar(dc);
        ;

        disabilityCarController.openRamp();
        driverView.showDisabilityDriverLeftCar();
        disabilityCarController.closeRamp();
    }

    @Override
    public void free() {
        disabilityParkingPlace.setParkingPlaceState(false);

        disabilityCarController.openRamp();
        driverView.showDisabilityDriverEnteredCar();
        disabilityCarController.closeRamp();

        disabilityParkingPlace.setCar(null);
    }
}
