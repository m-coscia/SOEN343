package src;

import java.sql.RowIdLifetime;

public class Profile {
    protected String name;

    public Profile(String n){
        name = n;
    }

    public void setName(String n) {
        name = n;
    }
}