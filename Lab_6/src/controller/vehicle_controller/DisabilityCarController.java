package controller.vehicle_controller;

import model.vehicle_model.DisabilityCar;
import view.vehicle_view.DisabilityCarView;

public class DisabilityCarController {

    private DisabilityCar disabilityCar;
    private DisabilityCarView disabilityCarView;

    public DisabilityCarController(DisabilityCar disabilityCar, DisabilityCarView disabilityCarView) {
        this.disabilityCar = disabilityCar;
        this.disabilityCarView = disabilityCarView;
    }

    public void setDisabilityCar(DisabilityCar disabilityCar) {
        this.disabilityCar = disabilityCar;
    }

    public void openRamp() {
        disabilityCar.setRampState(true);
        disabilityCarView.printOpenRamp(disabilityCar.getID());
    }

    public void closeRamp() {
        disabilityCar.setRampState(false);
        disabilityCarView.printCloseRamp(disabilityCar.getID());
    }
}
