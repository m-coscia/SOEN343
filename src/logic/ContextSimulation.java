package src.logic;

import src.Observer.Events.ActionEvent;
import src.Observer.Events.Event;
import src.Observer.Events.TimeEvent;
import src.components.Room;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class ContextSimulation {
    private SimulationParameter parameter;
    private String module;

    public ContextSimulation(SimulationParameter param){
        parameter = param;
        module = "SHC";
    }

    public void setModule(String mod){
        module = mod;
    }

    public String getModule(){
        return module;
    }

    public void changeTimeSpeed(double multiplier){
        parameter.getClock().changeSpeed(multiplier);
        String action = "Timestamp: " + parameter.getDate() + " " + parameter.getTime() + "\n" +
                "Module: "+ module + "\n" +
                "Event type: Change Time speed \n" +
                "Event description: The Simulation's current time speed has been modified \n" +
                "New time speed: " + multiplier + "\n";

    }

    public void startSimulation() throws IOException {
        parameter.getClock().start();

        // Run the while loop in a separate thread
        new Thread(() -> {
            while (parameter.getClock().isRunning().get()) {
                Event tempEvent = new TimeEvent("temperature", parameter); // Create a new event instance inside the loop
                try {
                    parameter.notifyTimeObserver(tempEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        parameter.setWeatherInside(parameter.getWeatherOutside());

        String action = "Timestamp: " + parameter.getDate() + " " + parameter.getTime() + "\n" +
                "Module: "+ module + "\n" +
                "Event type: Start Simulation \n" +
                "Event description: The Simulation has been started \n" +
                "Details: \n" +
                "Users: ";

        for(Profile p: parameter.getProfiles()){
            action += p.toString() +"\n";
        }

        action += "Rooms: ";
        for(Room r: parameter.getRooms()){
            action += r.toString() +"\n";
        }

        action += "Temperature outside: " + parameter.getWeatherOutside()+ "\n";
        action += "Temperature inside: " + parameter.getWeatherInside() +"\n";
        action += "LoggedIn user" + parameter.getLoggedIn() + "\n";

        ActionEvent actionEvent = new ActionEvent(module, action);
        parameter.notifyActionObserver(actionEvent);

    }

    public void stopSimulation() throws IOException {
        parameter.getClock().pause();
        String action = "Timestamp: " + parameter.getDate() + " " + parameter.getTime() + "\n" +
                "Module: "+ module + "\n" +
                "Event type: Stop Simulation \n" +
                "Event description: The Simulation has been stopped \n";

        ActionEvent actionEvent = new ActionEvent(module, action);
        parameter.notifyActionObserver(actionEvent);

    }

    public void modifyDate(LocalDate d){
        String action = "Timestamp: " + parameter.getDate() + " " + parameter.getTime() + "\n" +
                "Module: "+ module + "\n" +
                "Event type: Modify Date \n" +
                "Event description: The Simulation's current date has been modified \n" +
                "Old date: " + parameter.getDate() + "\n";
        parameter.setDate(d);
        action += "New date: " + parameter.getDate() + "\n";
    }

    public void modifyTime(LocalTime t){
        String action = "Timestamp: " + parameter.getDate() + " " + parameter.getTime() + "\n" +
                "Module: "+ module + "\n" +
                "Event type: Modify Time \n" +
                "Event description: The Simulation's current time has been modified \n" +
                "Old time: " + parameter.getTime() + "\n";
        parameter.setTime(t);
        action += "New time: " + parameter.getTime() + "\n";

    }

    public void moveLoggedUser(Room r){
        String action = "Timestamp: " + parameter.getDate() + " " + parameter.getTime() + "\n" +
                "Module: "+ module + "\n" +
                "Event type: Move Logged In User \n" +
                "Event description: The Simulation's current logged in user's location has been modified \n" +
                "Old Location: " + parameter.getLoggedIn().getLocation().toString() + "\n";
        parameter.getLoggedIn().setRoom(r);
        action += "New Location: " + parameter.getLoggedIn().getLocation().toString() + "\n";
    }

    public void moveUser(Profile p, Room r){
        String action = "Timestamp: " + parameter.getDate() + " " + parameter.getTime() + "\n" +
                "Module: "+ module + "\n" +
                "Event type: Move User to new Location \n" +
                "Event description: The User" + p.getName() + " 's Location has been modified \n"+
                "Old Location: " + p.getLocation().toString() + "\n";
        p.setRoom(r);
        action += "New Location: " + p.getLocation().toString() + "\n";
    }

    public void modifyWeatherOutside(double temp){
        String action = "Timestamp: " + parameter.getDate() + " " + parameter.getTime() + "\n" +
                "Module: "+ module + "\n" +
                "Event type: Modify Weather Outside \n" +
                "Event description: The Simulation's outside weather has been modified \n" +
                "Old Outside Weather: " + parameter.getWeatherOutside() + "\n";
        parameter.setWeatherOutside(temp);
        action += "New time: " + parameter.getWeatherOutside() + "\n";

    }

    public void blockWindowMovement(Room r){
        r.getWindows().setObstructed(true);
        String action = "Timestamp: " + parameter.getDate() + " " + parameter.getTime() + "\n" +
                "Module: "+ module + "\n" +
                "Event type: Windows Obstructed \n" +
                "Event description: The Simulation's windows of the room " + r.toString() +" have been obstructed by an arbitrary object \n";

    }
}
