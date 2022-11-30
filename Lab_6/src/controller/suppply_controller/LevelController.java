package controller.suppply_controller;

import java.util.List;

import model.parkingplace_model.ParkingPlace;
import model.supply_model.Level;
import view.supply_view.LevelView;

public class LevelController {

    private Level level;
    private LevelView levelView;

    public LevelController(Level level, LevelView levelView) {
        this.level = level;
        this.levelView = levelView;
    }

    public boolean levelIsFull() {
        List<ParkingPlace> places = level.getListOfParkingPlaces();

        for (int i = 0; i < places.size(); i++) {
            if (!places.get(i).getParkingPlaceState()) {
                levelView.printAvailablePlacesOnLevel(level.getNumber());
                return false;
            }
        }
        levelView.printNoAvailablePlacesOnLevel(level.getNumber());
        return true;
    }

    public int getCarPositionOnLevel(String id) {
        List<ParkingPlace> places = level.getListOfParkingPlaces();

        for (int i = 0; i < places.size(); i++) {
            if (places.get(i).getCar() != null && places.get(i).getCar().getID().compareTo(id) == 0) {
                return i;
            }
        }
        return -1;
    }

    public void showAvailableParkingPlacesOnLEvel(){
        int simple_counter = 0;
        int electric_counter = 0;
        int disability_counter = 0;

        List<ParkingPlace> places = level.getListOfParkingPlaces();
    
        for(int i = (int) (Level.simple_coef * level.getCapacity()) - 1; i >= 0; i--){
          if(places.get(i).getParkingPlaceState()){
            break;
          }
          simple_counter++;
        }
    
        for(int i = level.getCapacity() - 1; i > (int) ((1 - Level.electric_coef) * level.getCapacity()); i--){
          if(places.get(i).getParkingPlaceState()){
            break;
          }
          electric_counter++;
        }
    
        for(int i = (int) ((1 - Level.electric_coef) * level.getCapacity()) - 1; i > (int) (Level.simple_coef * level.getCapacity()); i--){
          if(places.get(i).getParkingPlaceState()){
            break;
          }
          disability_counter++;
        }
    
        levelView.printFreeParkingPlaces(level.getNumber(), simple_counter, electric_counter, disability_counter);
      }
}
