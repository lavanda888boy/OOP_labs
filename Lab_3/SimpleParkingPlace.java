public class SimpleParkingPlace extends ParkingPlace{

    public SimpleParkingPlace(){
        super();
    }

    @Override
    public void occupy(Car car){
        super.occupy(car);
        System.out.println("A "+ this.getClass().toString() +" was occupied by the car with id " + car.getID());
      }
}