package src.components;

import src.commands.Command;
import src.commands.TurnOffLightsCommand;
import src.commands.TurnOnLightsCommand;
import src.logic.Profile;

import javax.swing.*;
import java.util.ArrayList;

public class Room extends Component {
    private RoomType type;
    private Lights lights;
    private Windows windows;
    private Doors doors;
    private int numWindows;
    private int numLights;
    private int numDoors;
    private ArrayList<Profile> users;
    private boolean awayMode = false;
    private static int idCounter = 0;
    private final int identifier;
    private boolean isInZone = false;

    public Room(RoomType t, int windows, int lights, int doors, ArrayList<Profile> occupied){
        this.type = t;
        this.users = occupied;
        this.identifier = generateUniqueId();

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

    public Room(){
        this.identifier = generateUniqueId();
    }

    public boolean isInZone() {
        return isInZone;
    }

    public void setIsInZone(boolean isInZone){
        this.isInZone = isInZone;
    }

    public int generateUniqueId() {
        return ++idCounter;
    }

    public int getId(){
        return identifier;
    }

    //default constructor
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

    public ArrayList<Profile> getUsers(){
        return this.users;
    }

    public void setUsers(ArrayList<Profile> p){
        this.users = p;
    }

    public boolean isOccupied(){
        if(users.size()==0 || users == null){
            return false;
        }else{
            return true;
        }
    }

    //users is an array!!!!
    @Override
    public String toString() {
        return "Room{" +
                 "type=" + type +
                 ", numWindows=" + numWindows +
                 ", numLights=" + numLights +
                 ", numDoors=" + numDoors +
                 //", occupiedBy=" + (users != null && users.length > 0 ? users[0].getName() : "none") +
                 ", identifier=" + identifier +
                 '}';
    }

  
    // Method to check and adjust lighting based on autoMode and user presence
    // If user clicks automode on layout, you constantly call this method
    // If user clicks automode off, you don't call this method
    public static void checkAndSetLighting(Room room, Profile caller) {
        if (room.getLights() != null && room.getLights().getIsAutoMode()) {
            if (room.getUsers() != null) {
                // Assuming switchLightsOn is a command that takes a Lights object and turns it on
                room.setCommand(new TurnOnLightsCommand(room.getLights(), room.getUsers(), caller));
            } else {
                // Assuming switchLightsOff is a command that takes a Lights object and turns it off
                room.setCommand(new TurnOffLightsCommand(room.getLights(), room.getUsers(), caller));
            }
            room.executeCommand();
        }
    }

    public boolean isAwayMode(){
        return awayMode;
    }

    public void setAwayMode(boolean awayMode){
        this.awayMode = awayMode;
    }
}