package src.components;

import src.commands.Command;
import src.commands.TurnOffLightsCommand;
import src.commands.TurnOnLightsCommand;
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

    //default constructor
    public Room(){
        type = RoomType.BEDROOM;
        numWindows = 0;
        numLights = 0;
        numDoors = 0;
        lights = null;
        windows = null;
        doors = null;
        user = null;
    }

    //Parameterized Constructor
    public Room(RoomType t, int windows, int lights, int doors, Profile occupied){
        type = t;
        user = occupied;

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

    // Method to check and adjust lighting based on autoMode and user presence
    public static void checkAndSetLighting(Room room) {
        if (room.getLights() != null && room.getLights().getIsAutoMode()) {
            if (room.getUser() != null) {
                // Assuming switchLightsOn is a command that takes a Lights object and turns it on
                room.setCommand(new TurnOnLightsCommand(room.getLights()));
            } else {
                // Assuming switchLightsOff is a command that takes a Lights object and turns it off
                room.setCommand(new TurnOffLightsCommand(room.getLights()));
            }
            room.executeCommand();
        }
    }
}