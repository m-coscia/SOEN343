package src.Observer;

public class Event {
    private String eventType;

    public Event(String type){
        eventType = type;
    }

    public String getType(){
        return eventType;
    }
}
