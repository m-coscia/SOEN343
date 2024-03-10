package src;

public class Child extends Profile{
    private String userName;
    private String password;

    public Child(String n, String user, String pw) {
        super(n);
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
