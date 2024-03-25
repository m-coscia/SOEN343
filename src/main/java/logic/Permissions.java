package logic;

public class Permissions {
    private boolean windows;
    private boolean doors;
    private boolean garageDoor;
    private boolean lights;
    private boolean temperature;

    public Permissions(boolean w, boolean d, boolean g, boolean l, boolean t){
        windows = w;
        doors = d;
        garageDoor = g;
        lights = l;
        temperature = t;
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

    public void setTemperaturePermission(boolean permission){
        temperature = permission;
    }

    public boolean getTemperaturePermission(){
        return temperature;
     }
}
