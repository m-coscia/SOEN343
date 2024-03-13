package src.commands;

import src.components.Lights;
import src.logic.Profile;

public record TurnOffLightsCommand(Lights lights, Profile user) implements Command{
    @Override
    public void execute() {
        lights.switchLightsOff();
    }
}
