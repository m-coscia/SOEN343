package logic;

import java.security.Permission;

public class Profile {
    protected String name;
    protected Permissions permissions;

    public Profile(String n){
        name = n;
    }

    public void setName(String n) {
        name = n;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public Permissions getPermissions(){
        return permissions;
    }
}
