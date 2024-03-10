package logic;

enum roomType{
    BEDROOM,
    LIVINGROOM,
    BATHROOM,
    KITCHEN,
    GARAGE
}

public class Room {
    private roomType type;
    private int numWindows;
    private int numLights;
    private int numDoors;

    //default constructor
    public Room(){

    }

    //Parameterized Constructor
    public Room(roomType t, int windows, int lights, int doors){
        type = t;
        numWindows = windows;
        numLights = lights;
        numDoors = doors;
    }

    public void setType(roomType t){
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

    public roomType getType(){
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






}
