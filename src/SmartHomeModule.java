package src;

public abstract class SmartHomeModule {
    protected Permissions parentPermissions;
    protected Permissions childPermissions;
    protected Permissions guestPermissions;
    protected Permissions strangerPermissions;
    protected SimulationParameter parameter;

    public SmartHomeModule(){
    }

    public Permissions getParentPermissions(){
        return parentPermissions;
    }

    public Permissions getChildPermissions(){
        return parentPermissions;
    }

    public Permissions getGuestPermissions(){
        return parentPermissions;
    }

    public Permissions getStrangerPermissions(){
        return parentPermissions;
    }


    public abstract void setParentPermissions();
    public abstract void setChildPermissions();
    public abstract void setGuestPermissions();
    public abstract void setStrangerPermissions();

}
