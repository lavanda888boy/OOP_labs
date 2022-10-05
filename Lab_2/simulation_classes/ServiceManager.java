public class ServiceManager{

    private String name;

    public ServiceManager(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void openParking(Parking p){
        if(p.getWorkingState() == false){
            System.out.println("SM " + this.name + " opens the parking");
            p.open();
        } else{
            System.out.println("The parking is already opened");
        }
    }

    public void closeParking(Parking p){
        if(p.getWorkingState() == true){
            System.out.println("SM " + this.name + " closes the parking");
            p.close();
        } else{
            System.out.println("The parking is already closed");
        }
    }
}
