package src.commands;

import src.components.Lights;
import src.logic.Profile;

public record TurnOnLightsCommand(Lights lights, Profile[] users, Profile caller) implements Command{
    @Override
    public void execute() {
        lights.switchLightsOn();
    }
}
