package src.Observer;

public class ActionEvent extends Event {
    private String action;

    public ActionEvent(String type, String action){
        super(type);
        this.action = action;
    }
    
    public String getAction(){
        return action;
    }
}
