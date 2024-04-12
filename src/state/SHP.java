package src.state;

import src.Observer.ConsoleOutputObserver;
import src.Observer.Events.Event;
import src.components.*;
import src.logic.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SHP {
    ConsoleOutputObserver consoleObserver;
    int alertResponseTime;
    State state;

    State awayModeOnState;
    State awayModeOffState;

    ArrayList<Room> rooms;
    Profile currentUser;
    private ScheduledExecutorService scheduler;


    // constructor
    public SHP(ConsoleOutputObserver consoleObserver,
            int alertResponseTime, HouseLayout layout, Profile caller) {
        this.consoleObserver = consoleObserver;
        this.alertResponseTime = alertResponseTime;

        awayModeOnState = new AwayModeOn(this);
        awayModeOffState = new AwayModeOff(this);
        state = awayModeOffState;
        this.currentUser = caller;

        rooms = new ArrayList<>();
        for (Room room : layout.getRooms()) {
            rooms.add(room);
        }
        scheduler = Executors.newScheduledThreadPool(1);
    }

    // setState()
    public void setState(State state) {
        this.state = state;
        closeUp(this.rooms, this.currentUser);
        checkIsOpen(this.rooms, this.currentUser);
        if (state instanceof AwayModeOn) {
            startMonitoring();
        } else {
            stopMonitoring();
        }
    }

    private void startMonitoring() {
        scheduler.scheduleAtFixedRate(() -> {
            state.checkIsOpen(rooms, currentUser);
            state.checkTemp(rooms, currentUser);
        }, 0, 5, TimeUnit.MINUTES); // Checks every 5 minutes
    }

    private void stopMonitoring() {
        scheduler.shutdownNow();
        try {
            scheduler.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void checkIsOpen(ArrayList<Room> rooms, Profile profile) {
        // check if any doors or windows are open

    }

    public void closeUp(ArrayList<Room> rooms, Profile profile) {
        state.closeDoors(rooms, profile);
        state.closeWindows(rooms, profile);
    }

    public ConsoleOutputObserver getConsoleObserver() {
        return consoleObserver;
    }

    public void notifyConsoleOutputObserver(Event e) throws IOException {
        consoleObserver.update(e);
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
}