package src.logic;

import src.components.Room;

public class Child extends Profile{
    private String userName;
    private String password;

    public Child(String n, String user, String pw, Room loc) {
        super(n,loc);
        userName = user;
        password = pw;
    }

    public void setUserName(String ID){
        userName = ID;
    }

    public void setPassword(String pw){
        password = pw;
    }

}
