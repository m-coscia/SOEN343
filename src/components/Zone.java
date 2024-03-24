package src.components;

import java.util.ArrayList;

public class Zone {
    private ArrayList<Room> rooms;
    private double temperature;

    public Zone(ArrayList<Room> r, double temp){
        rooms = r;
        temperature = temp;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature(){
        return temperature;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room r){
        rooms.add(r);
    }

    public void removeRoom(Room r){
        rooms.remove(r);
    }
}
