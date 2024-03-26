package src.Observer.Events;

import src.logic.Profile;

public class UserEvent extends Event{
    private Profile user;

    public UserEvent(String type, Profile u){
        super(type);
        user= u;
    }

    public Profile getUser(){
        return user;
    }
}
