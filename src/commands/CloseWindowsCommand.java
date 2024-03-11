package src.commands;

import src.components.Windows;

public record CloseWindowsCommand(Windows windows) implements Command{
    @Override
    public void execute() {
        windows.closeWindowsCommand();
    }
}
