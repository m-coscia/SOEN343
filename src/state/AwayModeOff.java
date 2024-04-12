package src.state;

import src.components.Room;
import src.logic.Profile;

import java.util.ArrayList;

public class AwayModeOff implements State {
    SHP shp;

    public AwayModeOff(SHP shp) {
        this.shp = shp;
    }

    // TO BE IMPLEMENTED
    @Override
    public void openDoors(ArrayList<Room> rooms, Profile profile) {

    }

    @Override
    public void closeDoors(ArrayList<Room> rooms, Profile profile) {

    }

    @Override
    public void openWindows(ArrayList<Room> rooms, Profile profile) {

    }

    @Override
    public void closeWindows(ArrayList<Room> rooms, Profile profile) {

    }

    @Override
    public void checkIsOpen(ArrayList<Room> rooms, Profile profile) {
    }

    @Override
    public void checkTemp(ArrayList<Room> rooms, Profile profile) {
    }
}
