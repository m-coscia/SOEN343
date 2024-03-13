package src.components;

public class Windows {
    private boolean obstructed;
    private IconType icon = IconType.WINDOW;
    private boolean open = false;

    public void closeWindowsCommand() {
        this.open = false;
    }

    public void openWindowsCommand() {
        this.open = true;
    }
}
