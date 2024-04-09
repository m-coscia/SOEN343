package src;

import src.Observer.ActionObserver;
import src.Observer.Events.TimeEvent;
import src.Observer.TemperatureObserver;
import src.Observer.TimeObserver;
import src.components.Clock;
import src.components.Room;
import src.components.Zone;
import src.logic.*;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Controller {

    private static Controller controller = null;
    private TimeObserver to;

    private String layoutFileName;
    private String temperatureFile;
    private DataBase database;
    private SimulationParameter simParam = null;
    private ArrayList<Zone> zones = new ArrayList<>();
    private double avgTemp = 0;
    private Controller(){

    }

    public static Controller getController() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;

    }

    public void layoutSetUp(String filename) throws FileNotFoundException {
        try{
            HouseLayout.getHouseLayout().setHouseLayout(filename);
            database = DataBase.getDataBase();
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        System.out.println(filename);
        layoutFileName = filename;

    }


    public void saveProfiles(ArrayList<JTextField> nameFields, ArrayList<JTextField> usernameFields,
                             ArrayList<JPasswordField> passwordFields, ArrayList<JComboBox<String>> typeFields, ArrayList<JCheckBox> windowsCheckboxes,
                             ArrayList<JCheckBox> doorsCheckboxes, ArrayList<JCheckBox> garageCheckboxes,
                             ArrayList<JCheckBox> lightsCheckboxes, ArrayList<JComboBox<String>> locationFields) {

        for (int i = 0; i < nameFields.size(); i++){

            ArrayList<Room> rooms = HouseLayout.getHouseLayout().getRooms();
            Room room = null;
            for (Room r: rooms){
                if(String.valueOf(r.getType()).equals(locationFields.get(i).getSelectedItem())){
                    System.out.println(locationFields.get(i).getSelectedItem());
                    room = r;
//                    System.out.println(ArrayList.toString(r.getUsers()));
                    break;
                }

            }

//            if(locationFields.get(i).getSelectedItem().equals("BEDROOM")){
//
//            }else if (locationFields.get(i).getSelectedItem().equals("LIVING ROOM")){
//                //r = RoomType.LIVINGROOM;
//            } else if (locationFields.get(i).getSelectedItem().equals("BATHROOM")) {
//               // r = RoomType.BATHROOM;
//            } else if (locationFields.get(i).getSelectedItem().equals("KITCHEN")) {
//                //r = RoomType.KITCHEN;
//            }else if (locationFields.get(i).getSelectedItem().equals("GARAGE")) {
//                //r = RoomType.GARAGE;
//            }
            boolean window = windowsCheckboxes.get(i).isSelected();
            boolean door = doorsCheckboxes.get(i).isSelected();
            boolean garage = garageCheckboxes.get(i).isSelected();
            boolean lights = lightsCheckboxes.get(i).isSelected();

            Profile p = null;
            if (typeFields.get(i).getSelectedItem().equals("Parent")){

                p = new Parent(nameFields.get(i).getText(),
                        usernameFields.get(i).getText(),
                        passwordFields.get(i).getText(), room);

            }else if (typeFields.get(i).getSelectedItem().equals("Child")){

                p = new Child(nameFields.get(i).getText(),
                        usernameFields.get(i).getText(),
                        passwordFields.get(i).getText(),room );

            }else if (typeFields.get(i).getSelectedItem().equals("Guest")){

                p = new Guest(nameFields.get(i).getText(),
                        usernameFields.get(i).getText(),
                        passwordFields.get(i).getText(),room );

            }else if (typeFields.get(i).getSelectedItem().equals("Stranger")){

                p = new Stranger(nameFields.get(i).getText(), room);
            }
            Permissions permission = new Permissions(window,door,garage,lights, false);

            if(p != null){
                p.setPermissions(permission);
                database.addAccount(p);
                room.addUserToRoom(p);
                System.out.println( typeFields.get(i).getSelectedItem()+ " profile with name "+ p.getName()+ " was added to the database.");
            }else {
                System.out.println("Could not add profile with name " +nameFields.get(i).getText() );
            }


        }
    }

    public ArrayList<Profile> getProfiles(){
        return database.getProfiles();
    }

    public double getTemperature(){
        if(simParam == null){
           return 0;
        }else{
           return simParam.getWeatherOutside();
        }
    }

    public String getDate(){
        return String.valueOf(simParam.getDate());
    }
    public void login(Profile p){
        Login l = new Login(p);
//        if(p instanceof Parent){
//            if(((Parent) p).getUserName().equals(username) && ((Parent) p).getPassword().equals(password)){
//                 l = new Login(p);
//            }
//        } else if (p instanceof Child) {
//            if(((Child) p).getUserName().equals(username) && ((Child) p).getPassword().equals(password)){
//                l = new Login(p);
//            }
//        }else if (p instanceof Guest){
//            if(((Guest) p).getUserName().equals(username) && ((Guest) p).getPassword().equals(password)){
//                l = new Login(p);
//            }
//        }else {
//            l = new Login(p);
//        }
    }

    public void loginOtherUser(Profile p){
        simParam.login(p);
    }
    public Profile getCurrentLoggedInUser(){
        return simParam.getLoggedIn();
    }

    public void setCurrentLoggedInUser(Profile p){
        simParam.login(p);
    }
    public String getType(Profile profile) {
        if( profile instanceof Parent){
            return "Parent";
        }else if (profile instanceof Guest){
            return "Guest";
        }else if(profile instanceof Child){
            return "Child";
        }else{
            return "Stranger";
        }
    }

    public String getLocation(Profile profile){
       // return profile.getLocation().toString();
        if (profile.getLocation() == null){
            return "fix location";
        }else{
            return profile.getLocation().getType().toString();
        }

    }

    public ArrayList<Room> getRooms() {
        return HouseLayout.getHouseLayout().getRooms();
    }

    public void setZones(ArrayList<ArrayList<JCheckBox>> checkboxes, ArrayList<JTextField> temperatures, ArrayList<JComboBox<String>> typesOfZonesList){
        ArrayList<Room> rooms = getRooms();
        ArrayList<Zone> zones = new ArrayList<>();

        //read the inputs from user
        //for every zone added
        for (int i = 0; i < checkboxes.size(); i++){
            ArrayList<Room> roomsInCurrentZone = new ArrayList<>();
            //get the checkboxes for that zone
            ArrayList<JCheckBox> jCheckBoxes = checkboxes.get(i);
            System.out.print("For zone " + i + ": " );

            for (int j = 0; j < jCheckBoxes.size(); j++){
                //of the checkbox was selected in this zone
                if(jCheckBoxes.get(j).isSelected()){
                    //add corresponding room to the zone's list of rooms
                    roomsInCurrentZone.add(rooms.get(j));
                    System.out.print( j +",");
                }
            }

            //get the temperature that was set for this zone
            double temp = Double.parseDouble(temperatures.get(i).getText());
            avgTemp += temp;
            //if (temp < 0){
                String type = typesOfZonesList.get(i).getModel().getSelectedItem().toString();
                System.out.println("Zone " + i + ": " + type);
                Zone zone = new Zone(roomsInCurrentZone,temp,type);
                //TODO: set desired temp  and change the temp param above to the outside temp
                zones.add(zone);
//            }else{
//                Zone zone = new Zone(roomsInCurrentZone, temp, "HEATING");
//                zones.add(zone);
//            }
            System.out.println();
        }

        this.zones = zones;
        avgTemp = avgTemp / temperatures.size();
        //attachObservers(null,null,null,null);

    }

    //TODO chnage zone setting to setting the desired temperature bcz temp of zones is set to outside temp at first
    public void setZoneTemperature(double temp, Zone z){
        try{
            simParam.setZoneTemperature(temp,z);
        }catch(IOException e){
            System.out.println("Line: 232" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setZoneType(Zone zone, String type){
        simParam.setZoneType(zone,type);
    }

    public void setSimulationParams(String temperatureFile, Date date, int hours,int min, double outsideTemp, Profile profile) {

        try{
            this.temperatureFile = temperatureFile;
            simParam = new SimulationParameter(layoutFileName, temperatureFile ,date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalTime.of(hours,min), avgTemp,outsideTemp, new Login(profile));

            try{
                simParam.setZones(zones);
            }catch(IOException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            //attachObservers(null);

        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Clock getClock() {
        return simParam.getClock();
    }

    public void startSimulation() {
        try{
            simParam.startSimulation();
        }catch (IOException e){
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void stopSimulation() {
        try{
            simParam.stopSimulation();
        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
    public void changeSpeed(double multiplier){
        simParam.getClock().changeSpeed(multiplier);
    }

    public LocalTime getTime() {
        return simParam.getTime();
    }

    //todo fix the observers attached to use all the defined classes in logic
    public void attachObservers(JLabel clockDisplay, JLabel dateDisplay, JLabel tempLabel, JTextPane consoleText) {
        to = new TimeObserver(clockDisplay,dateDisplay,tempLabel);
//        simParam.attachTimeObserver(to);
        simParam.attachTimeObserver(to);
        simParam.attachActionObserver(new ActionObserver(consoleText));
        simParam.attachTemperatureObserver(new TemperatureObserver());

    }

    public String[] getExistingLocations(){
        ArrayList<Room> rooms = getRooms();
        String[] roomNames = new String[rooms.size()];
        for (int i = 0; i < rooms.size(); i++){
            roomNames[i] = rooms.get(i).getType().toString();
        }
        return roomNames;
    }

    public ArrayList<Zone> getZones() {
        return simParam.getZones();
    }

    public DataBase getDB() {
        return database;
    }

    public void changeUserLocation(Profile p, int location){
        if (location != -1){
            getRooms().get(location).addUserToRoom(p);
            p.setRoom(getRooms().get(location));

            System.out.println(p.getLocation().getType().toString() + " " + p.getLocation().getId());

           // System.out.println(getRooms().get(location).getUsers().toString());
        }else{
            System.out.println("no info changed for profile " + p.getName());
        }
    }

    public void setWeather(double v) {
        simParam.setWeatherOutside(v);
        to.update(new TimeEvent("user changed temperature in context of simulation", simParam));
    }

    public void changeTime(int hour, int min){
        LocalTime t = LocalTime.of(hour,min);
        simParam.setTime(t);
        to.update(new TimeEvent("user changed time in context of simulation", simParam));
    }

    public void setDate(LocalDate date) {
        simParam.setDate(date);
        to.update(new TimeEvent("date changed", simParam));
    }

    public void deleteProfile(Profile p){
        database.deleteAccount(p);
    }

    public void changeUserPassword(Profile p, String text) {
        if (p instanceof Parent){
            ((Parent)p).setUserName(text);
        }else if (p instanceof Child){
            ((Child)p).setUserName(text);
        }else if(p instanceof Guest){
            ((Guest)p).setUserName(text);
        }

    }

    public void changeProfileName(Profile p, String name) {
        p.setName(name);
    }

    public void changePermission(String permission, Profile p, boolean value) {
        switch (permission){
            case "Window":
                p.getPermissions().setWindowsPermission(value);
                break;
            case "Lights":
                p.getPermissions().setLightsPermission(value);
                break;
            case "Doors":
                p.getPermissions().setDoorsPermission(value);
                break;
            case "Garage":
                p.getPermissions().setGarageDoorPermission(value);
                break;
        }
    }
}
