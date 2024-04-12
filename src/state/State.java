package src.state;

import src.components.Room;
import src.logic.Profile;

import java.util.ArrayList;

public interface State {
    void openDoors(ArrayList<Room> rooms, Profile profile);
    void closeDoors(ArrayList<Room> rooms, Profile profile);
    void openWindows(ArrayList<Room> rooms, Profile profile);
    void closeWindows(ArrayList<Room> rooms, Profile profile);
}
