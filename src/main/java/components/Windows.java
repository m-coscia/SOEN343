package components;

public class Windows {
    private boolean obstructed;
    private IconType icon = IconType.WINDOW;
    private boolean open = false;

    public Windows() {
        obstructed = false;
        open = false;
    }

    public void closeWindowsCommand() {
        this.open = false;
    }

    public void openWindowsCommand() {
        this.open = true;
    }

    public void setObstructed(boolean blocked){
        obstructed = blocked;
    }

    public boolean getObstructed(){
        return obstructed;
    }


}
