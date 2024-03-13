package src.commands;

import src.components.Lights;

public record TurnOnLightsCommand(Lights lights) implements Command{
    @Override
    public void execute() {
        lights.switchLightsOn();
    }
}
