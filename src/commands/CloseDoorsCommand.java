package src.commands;

import src.components.Doors;
import src.logic.Profile;

public record CloseDoorsCommand(Doors doors, Profile user) implements Command{
    @Override
    public void execute() {

        //Verify if it is a Garage door

        //Close the doors
        doors.closeDoorsCommand();
    }
}
