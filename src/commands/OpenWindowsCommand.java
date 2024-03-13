package src.commands;

import src.components.Windows;

public record OpenWindowsCommand(Windows windows) implements Command{
    @Override
    public void execute() {
        windows.openWindowsCommand();
    }
}
