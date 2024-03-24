package commands;

import java.util.Arrays;

import components.Windows;
import logic.Parent;
import logic.Profile;

public class OpenWindowsCommand implements Command{
    private final Windows windows;
    private final Profile[] users;
    private final Profile caller;

    public OpenWindowsCommand(Windows windows, Profile[] users, Profile caller) {
        this.windows = windows;
        this.users = users;
        this.caller = caller;
    }

    @Override
    public void execute() {
        //verify if caller has permission to windows
        if (!caller.getPermissions().getWindowsPermission()){
            System.out.println("Windows cannot be controlled by you Stranger!!");
            return;
        } else {
            // verify caller is part of the users that are in the room calling the command
            boolean isCallerInRoom = Arrays.stream(users).anyMatch(user -> user.equals(caller));
            
            // if caller isn't in room, they can't control windows
            if (!isCallerInRoom){
                // if user isn't parent, you can't control windows if you're not in the room
                if(!(caller instanceof Parent)) {
                    // user has permission but isn't in room
                    System.out.println("You don't have permission to control windows when you're not in the room");
                    return;
                } else { // Parent can control windows from anywhere
                    System.out.println("Parent is closing windows from anywhere");
                    windows.openWindowsCommand();
                }
            } else { // caller is in room
                System.out.println("Closing Windows");
                windows.openWindowsCommand();
            }
        }
    }
}
