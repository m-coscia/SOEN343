package src;

import src.components.Room;

public class virtualTenant {
    private Profile p;
    private Room location;

    public virtualTenant(Profile tenant, Room loc){
        p = tenant;
        location = loc;
    }

    public void setLocation(Room loc){
        location = loc;
    }

    public Profile getProfile(){
        return p;
    }
}
