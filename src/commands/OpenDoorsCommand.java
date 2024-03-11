package src.commands;

import src.components.Doors;

public record OpenDoorsCommand(Doors doors) implements Command{
    @Override
    public void execute() {
        doors.openDoorsCommand();
    }
}
