package src.Observer.Events;

public class WindowEvent extends Event {
    private String eventType;

    // type is the type of module
    // action is the if the window is obstructed, or don't have the permissions
    public WindowEvent(String type, String eventType){
        super(type);
        this.eventType = eventType;
    }
    
    public String getEventType(){
        return eventType;
    }
}
