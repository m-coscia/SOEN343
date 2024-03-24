package src.main.java.logic;

import src.main.java.components.Room;

public class Profile {
    protected String name;
    protected Permissions permissions;
    protected Room location;

    public Profile(String n, Room loc){
        name = n;
        location = loc;
    }

    public void setName(String n) {
        name = n;
    }

    public String getName(){
        return name;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public Permissions getPermissions(){
        return permissions;
    }

    public Room getLocation(){
        return location;
    }

    public void setRoom(Room loc){
        location = loc;
    }

    @Override
    public String toString() {
        return "Profile{" +
               "name='" + name + '\'' +
               ", permissions=" + permissions +
               ", located in room=" + (location != null ? location.getId() : "none") +
               '}';
    }

}
