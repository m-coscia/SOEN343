package src.Observer;

import java.io.*;

import src.Observer.Events.ActionEvent;
import src.Observer.Events.Event;

public class ActionObserver implements Observer {

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

            // Close the writer
            bw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            // Handle any exceptions that occur during file writing
        }

    }

}
