package src.main.java.logic;

import src.main.java.components.Room;
import src.main.java.SmartHomeModule;

import java.time.LocalDate;
import java.time.LocalTime;

public class ContextOfSimulation {
    private SmartHomeModule module;

    public ContextOfSimulation(){

    }

    //move the logged user to a room
    public void moveLoggedUser(Room r){
        module.getParameter().getLoggedIn().setRoom(r);
    }

    //move any user to a room
    public void moveUser(Profile p, Room r){
        p.setRoom(r);
    }

    //modifies the date of the simulation
    public void modifyDate(LocalDate date){
        module.getParameter().setDate(date);
    }

    //modifies the time of the simulation
    public void modifyTime(LocalTime time){
        module.getParameter().setTime(time);
    }

    public void blockWindows(Room r){
        int index = module.getParameter().getLayout().getRooms().indexOf(r);
        module.getParameter().getLayout().getRooms().get(index).getWindows().setObstructed(true);
    }

    public void unBlockWindows(Room r){
        int index = module.getParameter().getLayout().getRooms().indexOf(r);
        module.getParameter().getLayout().getRooms().get(index).getWindows().setObstructed(false);
    }


}
