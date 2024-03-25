package commands;

import components.Doors;
import logic.Profile;

public class OpenDoorsCommand implements Command{
    private final Doors doors;
    private final Profile[] users;
    private final Profile caller;


    public OpenDoorsCommand(Doors doors, Profile[] users, Profile caller) {
        this.doors = doors;
        this.users = users;
        this.caller = caller;
    }
    
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
