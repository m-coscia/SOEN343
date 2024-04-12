package src.Observer.Events;

public class DoorEvent extends Event{
    private String eventType;

    public DoorEvent(String type, String eventType){
        super(type);
        this.eventType = eventType;
    }

    public String getEventType(){
        return eventType;
    }
}
