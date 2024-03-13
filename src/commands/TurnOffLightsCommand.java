package src.commands;

import src.components.Lights;

public record TurnOffLightsCommand(Lights lights) implements Command{
    @Override
    public void execute() {
        lights.switchLightsOff();
    }
}
