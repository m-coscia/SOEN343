package src.commands;

import src.components.Doors;

public record CloseDoorsCommand(Doors doors) implements Command{
    @Override
    public void execute() {
        doors.closeDoorsCommand();
    }
}
