package src.commands;

import java.util.ArrayList;

import src.components.Lights;
import src.logic.Parent;
import src.logic.Profile;

public record TurnOnLightsCommand(Lights lights, ArrayList<Profile> users, Profile caller) implements Command{
    @Override
    public void execute() {
        //verify if caller has permission to lights
        if (!caller.getPermissions().getLightsPermission()){
            System.out.println("Lights cannot be controlled by you Stranger!!");
            return;
        } else {
            // verify caller is part of the users that are in the room calling the command
            boolean isCallerInRoom = users.contains(caller);

            // if caller isn't in room, they can't control lights
            if (!isCallerInRoom){
                // if user isn't parent, you can't control lights if you're not in the room
                if(!(caller instanceof Parent)) {
                    // user has permission but isn't in room
                    System.out.println("You don't have permission to control lights when you're not in the room");
                    return;
                } else { // Parent can control lights from anywhere
                    System.out.println("Parent is turning on lights from anywhere");
                    lights.switchLightsOn();
                }
            } else { // caller is in room
                System.out.println("Turning On Lights");
                lights.switchLightsOn();
            }
        }
    }
}
