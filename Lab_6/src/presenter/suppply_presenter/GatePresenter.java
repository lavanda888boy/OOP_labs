package presenter.suppply_presenter;

import connection_interfaces.WorkingStateInterface;
import model.supply_model.Gate;
import view.supply_view.GateView;

public class GatePresenter implements WorkingStateInterface {

    private Gate gate;
    private GateView gateView;

    public GatePresenter(Gate gate, GateView gateView) {
        this.gate = gate;
        this.gateView = gateView;
    }

    @Override
    public void open() {
        gate.setGateState(true);
        gateView.printGateOpened();
    }

    @Override
    public void close() {
        gate.setGateState(false);
        gateView.printGateClosed();
    }
}
