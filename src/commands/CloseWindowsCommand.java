package src.commands;

import src.Observer.ConsoleOutputObserver;
import src.Observer.Events.Event;
import src.Observer.Events.WindowEvent;
import src.components.Windows;
import src.logic.Parent;
import src.logic.Profile;

import java.io.IOException;
import java.util.ArrayList;

public record CloseWindowsCommand(Windows windows, ArrayList<Profile> users, Profile caller) implements Command {
    
    public static ConsoleOutputObserver consoleObserver;
    public static WindowEvent windowEvent;

    public void notifyConsoleOutputObserver(Event e) throws IOException {
        consoleObserver.update(e);
    }
    
    @Override
    public void execute() throws IOException {
        // verify if caller has permission to windows
        if (!caller.getPermissions().getWindowsPermission()) {
            System.out.println("Windows cannot be controlled by you Stranger!!");

            return;
        } else if (windows.getObstructed()) {
            // verify if windows are obstructed
            String event = "Windows are obstructed, can't close them";
            System.out.println(event);
            windowEvent = new WindowEvent("windowEvent", event);
            notifyConsoleOutputObserver(windowEvent);
            
            return;
        } else {
            // verify caller is part of the users that are in the room calling the command
            boolean isCallerInRoom = users.contains(caller);

            // if caller isn't in room, they can't control windows
            if (!isCallerInRoom) {
                // if user isn't parent, you can't control windows if you're not in the room
                if (!(caller instanceof Parent)) {
                    // user has permission but isn't in room
                    System.out.println("You don't have permission to control windows when you're not in the room");
                    return;
                } else { // Parent can control windows from anywhere
                    System.out.println("Parent is closing windows from anywhere");
                    windows.closeWindowsCommand();
                }
            } else { // caller is in room
                System.out.println("Closing Windows");
                windows.closeWindowsCommand();
            }
        }
    }
}
