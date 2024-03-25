package commands;

import java.util.Arrays;

import components.Lights;
import logic.Parent;
import logic.Profile;

public class TurnOnLightsCommand implements Command{
    private final Lights lights;
    private final Profile[] users;
    private final Profile caller;

    public TurnOnLightsCommand(Lights lights, Profile[] users, Profile caller) {
        this.lights = lights;
        this.users = users;
        this.caller = caller;
    }

    @Override
    public void execute() {
        //verify if caller has permission to lights
        if (!caller.getPermissions().getLightsPermission()){
            System.out.println("Lights cannot be controlled by you Stranger!!");
            return;
        } else {
            // verify caller is part of the users that are in the room calling the command
            boolean isCallerInRoom = Arrays.stream(users).anyMatch(user -> user.equals(caller));

            // if caller isn't in room, they can't control lights
            if (!isCallerInRoom){
                // if user isn't parent, you can't control lights if you're not in the room
                if(!(caller instanceof Parent)) {
                    // user has permission but isn't in room
                    System.out.println("You don't have permission to control lights when you're not in the room");
                    return;
                } else { // Parent can control lights from anywhere
                    System.out.println("Parent is closing lights from anywhere");
                    lights.switchLightsOn();
                }
            } else { // caller is in room
                System.out.println("Closing Lights");
                lights.switchLightsOn();
            }
        }
    }
}
