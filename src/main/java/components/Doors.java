package components;

public class Doors {
    private IconType icon = IconType.DOOR;
    private String doorType;
    public boolean open = false;
    private boolean isGarageDoor = false;

    public Doors(boolean isGarageDoor) {
        this.open = false;
        this.isGarageDoor = isGarageDoor;
    }

    public void closeDoorsCommand(){
        this.open = false;
        System.out.println("Door is closed");
    }
    
    public void openDoorsCommand(){
        this.open = true;
        System.out.println("Door is open");
    }

    public void setGarageDoor(boolean isGarageDoor){
        this.isGarageDoor = isGarageDoor;
    }

    public boolean getIsGarageDoor(){
        return isGarageDoor;
    }
}
