package src.commands;

import src.components.Doors;
import src.logic.Profile;

public record CloseDoorsCommand(Doors doors, Profile[] users, Profile caller) implements Command{
    @Override
    public void execute() {

        // Verify if it is a garage door
        if(doors.getIsGarageDoor()){
            if (!user.getPermissions().getGarageDoorPermission()){
                System.out.println("Garage doors cannot be controlled by you!!");
                return;
            } else if (!user.getPermissions().getDoorsPermission()){
                System.out.println("Doors cannot be controlled by you!!");
                return;
            }
            else {
                // Close the garage doors
                System.out.println("Closing Garage Doors");
                doors.closeDoorsCommand();
                return;
            }
        } else if (!user.getPermissions().getDoorsPermission()){
            System.out.println("Doors cannot be controlled by you!!");
            return;
        } else {
            // Close the doors
            doors.closeDoorsCommand();
            return;
        }
    }
}
