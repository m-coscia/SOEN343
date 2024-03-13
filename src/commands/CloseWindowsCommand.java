package src.commands;

import src.components.Windows;
import src.logic.Profile;

public record CloseWindowsCommand(Windows windows, Profile user) implements Command{
    @Override
    public void execute() {
        windows.closeWindowsCommand();
    }
}
