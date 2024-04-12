package src.state;

import src.Observer.ConsoleOutputObserver;
import src.Observer.Events.DoorEvent;
import src.Observer.Events.WindowEvent;
import src.components.*;
import src.logic.*;
import java.util.ArrayList;

public class SHP {
    DoorEvent doorEvent;
    WindowEvent windowEvent;
    ConsoleOutputObserver consoleObserver;
    int alertResponseTime;
    State state;
    AwayModeOn awayModeOn;
    AwayModeOff awayModeOff;

    // constructor
    public SHP(DoorEvent doorEvent, WindowEvent windowEvent, ConsoleOutputObserver consoleObserver, int alertResponseTime){
        this.doorEvent = doorEvent;
        this.windowEvent = windowEvent;
        this.consoleObserver = consoleObserver;
        this.alertResponseTime = alertResponseTime;
        this.awayModeOff = new AwayModeOff(this);
        this.awayModeOn = new AwayModeOn(this);
        this.state = awayModeOff;
    }
    // setState()
    public void setState(State state){
        this.state = state;
    }

    // getters and setters
    public DoorEvent getDoorEvent() {
        return doorEvent;
    }
    public void setDoorEvent(DoorEvent doorEvent) {
        this.doorEvent = doorEvent;
    }
    public WindowEvent getWindowEvent() {
        return windowEvent;
    }
    public void setWindowEvent(WindowEvent windowEvent) {
        this.windowEvent = windowEvent;
    }
    public ConsoleOutputObserver getConsoleObserver() {
        return consoleObserver;
    }
    public void setConsoleObserver(ConsoleOutputObserver consoleObserver) {
        this.consoleObserver = consoleObserver;
    }
    public int getAlertResponseTime() {
        return alertResponseTime;
    }
    public void setAlertResponseTime(int alertResponseTime) {
        this.alertResponseTime = alertResponseTime;
    }


    public void openDoors(ArrayList<Room> rooms, Profile profile){
        // call state implementation
        state.openDoors(rooms, profile);
    }

    public void closeDoors(ArrayList<Room> rooms, Profile profile) {
        // call state implementation
        state.closeDoors(rooms, profile);
    }

    public void openWindows(ArrayList<Room> rooms, Profile profile) {
        // call state implementation
        state.openWindows(rooms, profile);
    }

    public void closeWindows(ArrayList<Room> rooms, Profile profile) {
        // call state implementation
        state.closeWindows(rooms, profile);
    }

    public State getState() {
        return this.state;
    }

    public State getAwayModeOnState(){
        return awayModeOn;
    }

    public State getAwayModeOffState(){
        return awayModeOff;
    }

}
