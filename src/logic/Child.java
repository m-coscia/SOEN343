package logic;

public class Child extends Profile{
    private String userName;
    private String password;

    public Child(String n, String user, String pw) {
        super(n);
        userName = user;
        password = pw;
    }
}
