package src.commands;

import src.components.Doors;
import src.logic.Profile;

import java.util.ArrayList;

public record OpenDoorsCommand(Doors doors, ArrayList<Profile> users, Profile caller) implements Command{
    @Override
    public void execute() {
        // Verify if it is a garage door
        if(doors.getIsGarageDoor()){
            // Verify if the user has permission to Garage Doors
            if (!caller.getPermissions().getGarageDoorPermission()){
                System.out.println("Garage doors cannot be controlled by you!!");
                return;
            } else if (!caller.getPermissions().getDoorsPermission()){  // Verify if the user has permission to Doors
                System.out.println("Doors cannot be controlled by you!!");
                return;
            }
            else if(!caller.getPermissions().getShpPermission() && caller.getLocation().getId()!=0){ //Verify if the user has permission to SHP module
                System.out.println("Doors cannot be controlled by you!!");
                return;
            }
            else {
                // Open the garage doors
                System.out.println("Opening Garage Doors");
                doors.openDoorsCommand();
                return;
            }
        } else if (!caller.getPermissions().getDoorsPermission()){  // Verify if the user has permission to Doors
            System.out.println("Doors cannot be controlled by you!!");
            return;
        } else {
            // Close the doors
            doors.openDoorsCommand();
            return;
        }
    }
}
