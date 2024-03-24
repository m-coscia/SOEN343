package src.Observer;

import src.logic.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AccountObserver implements Observer {
    private File accountLog = new File("accountLog.txt");

    @Override
    public void update(Event event) throws IOException{
        if(event.getType().equals("add")){
            Profile user = ((UserEvent) event).getUser();
            try (FileWriter writer = new FileWriter(accountLog, true)) {
                // Text to write to the file
                String profileInfo="";
                if(user instanceof Parent){
                    profileInfo += "PARENT,";
                    profileInfo += user.getName() + ",";
                    profileInfo += ((Parent) user).getUserName() +",";
                    profileInfo += ((Parent) user).getPassword() +",";
                    profileInfo += user.getLocation().getId() +"\n";
                }else if(user instanceof Child){
                    profileInfo += "CHILD,";
                    profileInfo += user.getName() + ",";
                    profileInfo += ((Child) user).getUserName() +",";
                    profileInfo += ((Child) user).getPassword() +",";
                    profileInfo += user.getLocation().getId() +"\n";
                }else if(user instanceof Guest){
                    profileInfo += "GUEST,";
                    profileInfo += user.getName() + ",";
                    profileInfo += ((Guest) user).getUserName() +",";
                    profileInfo += ((Guest) user).getPassword() +",";
                    profileInfo += user.getLocation().getId() +"\n";
                }else{
                    profileInfo += "STRANGER,";
                    profileInfo += user.getName() + ",";
                    profileInfo += user.getLocation().getId() +"\n";
                }


                // Write the text to the file
                writer.write(profileInfo);
                writer.close();


                System.out.println("Text has been written to the file successfully.");
            } catch (IOException e) {
                System.err.println("An error occurred while writing to the file: " + e.getMessage());
            }

        }else{
            System.out.println("Removing account from database...");
        }

    }
}
