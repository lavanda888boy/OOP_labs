package model.parkingplace_model;

public class ElectricParkingPlace extends ParkingPlace {
    
    private int currentVolume;
    private int chargerCapacity;

    public ElectricParkingPlace(int chargerCapacity){
        super();
        this.chargerCapacity = chargerCapacity;
        this.currentVolume = chargerCapacity;
    }

    public int getChargerCapacity(){
        return this.chargerCapacity;
    }

    public int getCurrentVolume(){
        return this.currentVolume;
    }

    public void setCurrentVolume(int volume){
        currentVolume = volume;
    }
}
