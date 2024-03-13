package src.components;

public class Doors {
    private IconType icon = IconType.DOOR;
    private String doorType;
    private boolean open = false;

    public void closeDoorsCommand(){
        this.open = false;
        System.out.println("Door is closed");
    }
    
    public void openDoorsCommand(){
        this.open = true;
        System.out.println("Door is open");
    }
}
