public class ServiceManager{

    private String name;

    public ServiceManager(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void openParking(Parking p){
        if(p.getWorkingState() = false){
            p.open();
            System.out.println("SM " + this.name + " opens the parking");
        } else{
            System.out.println("The parking is already opened");
        }
    }

    public void closeParking(Parking p){
        if(p.getWorkingState() = true){
            p.close();
            System.out.println("SM " + this.name + " closes the parking");
        } else{
            System.out.println("The parking is already closed");
        }
    }
}
