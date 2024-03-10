package logic;

public class Parent extends Profile{
    private String userName;
    private String password;

    public Parent(String n, String user,String pw ) {
        super(n);
        userName = user;
        password = pw;
    }
}
