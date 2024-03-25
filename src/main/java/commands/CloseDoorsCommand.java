package commands;

import components.Doors;
import logic.Profile;

public class CloseDoorsCommand implements Command{
    private final Doors doors;
    private final Profile[] users;
    private final Profile caller;

    public CloseDoorsCommand(Doors doors, Profile[] users, Profile caller) {
        this.doors = doors;
        this.users = users;
        this.caller = caller;
    }

    @Override
    public void execute() {

        // Verify if it is a garage door
        if(doors.getIsGarageDoor()){
            if (!caller.getPermissions().getGarageDoorPermission()){
                System.out.println("Garage doors cannot be controlled by you!!");
                return;
            } else if (!caller.getPermissions().getDoorsPermission()){
                System.out.println("Doors cannot be controlled by you!!");
                return;
            }
            else {
                // Close the garage doors
                System.out.println("Closing Garage Doors");
                doors.closeDoorsCommand();
                return;
            }
        } else if (!caller.getPermissions().getDoorsPermission()){
            System.out.println("Doors cannot be controlled by you!!");
            return;
        } else {
            // Close the doors
            doors.closeDoorsCommand();
            return;
        }
    }
}
