package src.Observer;

import java.io.*;

public class ActionObserver{

    public void update(Event e){
        try {
            // Open the file in append mode
            FileWriter fw = new FileWriter("ActionLog.txt", true);
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
