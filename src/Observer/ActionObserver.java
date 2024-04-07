package src.Observer;

import src.Observer.Events.ActionEvent;
import src.Observer.Events.Event;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ActionObserver implements Observer {
    JTextPane console = null;

    public ActionObserver(){

    }

    public ActionObserver(JTextPane console){
        this.console = console;
    }
    public void update(Event e){
        try {
            // Open the file in append mode
            FileWriter fw = new FileWriter("");
            switch(e.getType()){
                case "SHS":
                    fw = new FileWriter("SHSActionLog.txt", true);
                case "SHC":
                    fw = new FileWriter("SHCActionLog.txt", true);
                case "SHH":
                    fw = new FileWriter("SHHActionLog.txt", true);
                case "SHP":
                    fw = new FileWriter("SHPActionLog.txt", true);
            }

            BufferedWriter bw = new BufferedWriter(fw);

            // Write the event string to the file
            bw.write(((ActionEvent)e).getAction());
            bw.newLine(); // Add a new line for the next event

            if(console != null){
                console.setText(console.getText() + "\n" + ((ActionEvent) e).getAction());
            }

            // Close the writer
            bw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            // Handle any exceptions that occur during file writing
        }

    }

}
