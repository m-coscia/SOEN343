package src.commands;

import src.components.Doors;
import src.logic.Profile;

public record OpenDoorsCommand(Doors doors, Profile user) implements Command{
    @Override
    public void execute() {
        doors.openDoorsCommand();
    }
}
