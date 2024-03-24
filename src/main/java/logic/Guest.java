package logic;

import components.Room;

public class Guest extends Profile{
    private String userName;
    private String password;

    public Guest(String n, String user,String pw, Room loc) {
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

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }


}
