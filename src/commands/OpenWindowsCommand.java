package src.commands;

import src.components.Windows;
import src.logic.Profile;

public record OpenWindowsCommand(Windows windows, Profile user) implements Command{
    @Override
    public void execute() {
        windows.openWindowsCommand();
    }
}
