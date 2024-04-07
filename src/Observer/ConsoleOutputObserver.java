package src.Observer;  

import src.Observer.Events.Event;
import src.Observer.Events.WindowEvent;
import src.UI.Dashboard;

import javax.swing.*;

public class ConsoleOutputObserver implements Observer {
    private Dashboard dashboard;
    private JTextPane consoleArea;

    public  ConsoleOutputObserver(){}
    public ConsoleOutputObserver(JTextPane consoleArea){
        this.consoleArea = consoleArea;
    }
    public void update(Event e){
        
        // Get the event happening
        String eventString = ((WindowEvent)e).getEventType();

        // Print it to the dashboard (outputArea) 
        if (dashboard != null){
            dashboard.outputArea.append(eventString + "\n");
        }

        if(consoleArea != null){
            consoleArea.setText(consoleArea.getText() + "\n" + eventString);
        }
    }
}
