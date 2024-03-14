package src.logic;

public class Permissions {
    private boolean windows;
    private boolean doors;
    private boolean garageDoor;
    private boolean lights;

    public Permissions(boolean w, boolean d, boolean g, boolean l){
        windows = w;
        doors = d;
        garageDoor = g;
        lights = l;
    }

    public void setWindowsPermission(boolean permission){
        windows = permission;
    }

    public boolean getWindowsPermission(){
        return windows;
    }

    public void setDoorsPermission(boolean permission){
        doors = permission;
    }

    public boolean getDoorsPermission(){
        return doors;
    }

    public void setGarageDoorPermission(boolean permission){
        garageDoor = permission;
    }

    public boolean getGarageDoorPermission(){
        return garageDoor;
    }

    public void setLightsPermission(boolean permission){
        lights = permission;
    }

    public boolean getLightsPermission(){
        return lights;
    }
}
