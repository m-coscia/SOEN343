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
    private Profile[] users;
    private boolean awayMode = false;

    //default constructor
    public Room(){
        type = RoomType.BEDROOM;
        numWindows = 0;
        numLights = 0;
        numDoors = 0;
        lights = null;
        windows = null;
        doors = null;
        users = null;
    }

    //Parameterized Constructor
    public Room(RoomType t, int windows, int lights, int doors, Profile[] occupied){
        this.type = t;
        this.users = occupied;

        numWindows = windows;
        if(lights > 0)
            this.lights = new Lights();
        else 
            this.lights = null;

        numLights = lights;
        if (doors > 0) {
            if (t == RoomType.GARAGE) {
                this.doors = new Doors(true);
            } else {
                this.doors = new Doors(false);
            }
        } else {
            this.doors = null;
        }
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

    public Profile[] getUsers(){
        return users;
    }

    public void setUsers(Profile[] p){
        int lengthOfUsers = p.length;
        for (int i = 0; i < p.length; i++) {
            users[i] = p[i];
        }
    }

    public boolean isOccupied(){
        if(users==null){
            return false;
        }else{
            return true;
        }
    }

    // Method to check and adjust lighting based on autoMode and user presence
    public static void checkAndSetLighting(Room room) {
        if (room.getLights() != null && room.getLights().getIsAutoMode()) {
            if (room.getUsers() != null) {
                // Assuming switchLightsOn is a command that takes a Lights object and turns it on
                room.setCommand(new TurnOnLightsCommand(room.getLights(), room.getUsers(), null));
            } else {
                // Assuming switchLightsOff is a command that takes a Lights object and turns it off
                room.setCommand(new TurnOffLightsCommand(room.getLights(), room.getUsers(), null));
            }
            room.executeCommand();
        }
    }

    public boolean isAwayMode() {
        return awayMode;
    }

    public void setAwayMode(boolean awayMode) {
        this.awayMode = awayMode;
    }
}