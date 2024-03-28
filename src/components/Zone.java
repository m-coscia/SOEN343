package src.components;

import java.util.ArrayList;

public class Zone {
    private ArrayList<Room> rooms;
    private double temperature;
    private String type;

    public Zone(ArrayList<Room> r, double temp, String t) {
        if (!t.equals("HEATING") && !t.equals("COOLING")) {
            throw new IllegalArgumentException("Type must be either 'HEATING' or 'COOLING'");
        }
        rooms = r;
        temperature = temp;
        type = t;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature(){
        return temperature;
    }

    public void setRooms(ArrayList<Room> rooms) {
        for(Room r:rooms){
            r.setIsInZone(true);
        }
        this.rooms = rooms;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room r){
        r.setIsInZone(true);
        rooms.add(r);
    }

    public void removeRoom(Room r){
        r.setIsInZone(false);
        rooms.remove(r);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (!type.equals("HEATING") && !type.equals("COOLING")) {
            throw new IllegalArgumentException("Type must be either 'HEATING' or 'COOLING'");
        }
        this.type = type;
    }
}
