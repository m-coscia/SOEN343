package src.components;

import src.logic.Profile;

public class Room extends Component {
    private RoomType type;
    private final Lights lights;
    private final Windows windows;
    private final Doors doors;
    private int numWindows;
    private int numLights;
    private int numDoors;
    private Profile user;
    private static int idCounter = 0;
    private final int identifier;

    //default constructor
//    public Room(){
//        identifier = generateUniqueId();
//        type = RoomType.BEDROOM;
//        numWindows = 0;
//        numLights = 0;
//        numDoors = 0;
//        lights = null;
//        windows = null;
//        doors = null;
//        user = null;
//
//    }

    public int generateUniqueId() {
        return ++idCounter;
    }

    public int getId(){
        return identifier;
    }


    //Parameterized Constructor
    public Room(RoomType t, int windows, int lights, int doors, Profile occupied){
        type = t;
        user = occupied;
        identifier = generateUniqueId();


        numWindows = windows;
        if(lights > 0)
            this.lights = new Lights();
        else 
            this.lights = null;

        numLights = lights;
        if(doors > 0)
            this.doors = new Doors();
        else    
            this.doors = null;

        numDoors = doors;
        if(windows > 0)
            this.windows = new Windows();
        else 
            this.windows = null;
    }

    public void setType(RoomType t){
        type = t;
    }

    public void setNumWindows(int windows){
        numWindows = windows;
    }

    public void setNumLights(int lights){
        numLights = lights;
    }

    public void setNumDoors(int doors){
        numDoors = doors;
    }

    public RoomType getType(){
        return type;
    }

    public int getNumWindows(){
        return numWindows;
    }

    public int getNumLights(){
        return numLights;
    }

    public int getNumDoors(){
        return numDoors;
    }

    public Lights getLights() {
        return lights;
    }

    public Windows getWindows() {
        return windows;
    }

    public Doors getDoors() {
        return doors;
    }

    public Profile getUser(){
        return user;
    }

    public void setUser(Profile p){
        user = p ;
    }

    public boolean isOccupied(){
        if(user==null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public String toString() {
        return "Room{" +
               "type=" + type +
               ", numWindows=" + numWindows +
               ", numLights=" + numLights +
               ", numDoors=" + numDoors +
               ", occupiedBy=" + (user != null ? user.getName() : "none") +
               ", identifier=" + identifier +
               '}';
    }

}