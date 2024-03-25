package logic;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import Observer.*;
import components.Clock;
import components.Room;
import components.Zone;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SimulationParameter {
    private HouseLayout layout = HouseLayout.getHouseLayout();
    private DataBase db = DataBase.getDataBase();
    private LocalDate date;
    private Login login;
    private double weatherInside;
    private double weatherOutside;
    private AccountObserver accountObserver;
    private TimeObserver timeObserver;
    private Clock clock;
    private LocalTime t;
    private Map<String, Double> weatherData = new HashMap<>();
    private ArrayList<Zone> zones = new ArrayList<>();


    public SimulationParameter(String layoutFile, String tempFile, LocalDate d, LocalTime t, double inside, double outside, Login loggedIn) throws FileNotFoundException {
        layout.setHouseLayout(layoutFile);
        clock = new Clock();
        clock.setTime(t);
        clock.setDate(d);
        weatherInside = inside;
        weatherOutside = outside;
        login = loggedIn;
        db.setRooms(layout.getRooms());
        uploadTempFile(tempFile);
    }


    public Clock getClock(){
        return clock;
    }

    public void notifyAccountObserver(Event e) throws IOException {
        accountObserver.update(e);
    }

    public void notifyTimeObserver(Event e) throws IOException {
        timeObserver.update(e);
    }

    public Map getWeatherData(){
        return weatherData;
    }

    public void attachAccountObserver(AccountObserver o){
        accountObserver=o;
    }
  
    public void attachTimeObserver(TimeObserver o){
        timeObserver=o;
    }


    public HouseLayout getLayout(){
        return layout;
    }

    public LocalTime getTime(){
        return clock.getTime();
    }

    public LocalDate getDate(){
        return date;
    }

    public void setTime(LocalTime t){
        clock.setTime(t);
    }

    public void setDate(LocalDate d){
        date = d;
    }

    public double getWeatherInside(){
        return weatherInside;
    }

    public double getWeatherOutside(){
        return weatherOutside;
    }

    public void setWeatherOutside(double temp){
        weatherOutside = temp;
    }

    public void login(Profile user){
        login = new Login(user);
    }

    public Profile getLoggedIn(){
        return login.getCurrentUser();
    }

    //creates parent account
    public void createParentAccount(String name, String id, String pw, Room loc) throws IOException{
        if(!layout.getRooms().contains(loc)){
            System.out.println("ERROR: No such room in the house");
        }else {
            Profile p = new Parent(name, id, pw, loc);
            db.addAccount(p);
            login.setCurrentUser(p);
            UserEvent e = new UserEvent("add",p);
            notifyAccountObserver(e);
        }
    }

    //creates a child account
    public void createChildAccount(String name, String id, String pw, Room loc) throws IOException{
        if(!layout.getRooms().contains(loc)){
            System.out.println("ERROR: No such room in the house");
        }else {
            Profile p = new Child(name, id, pw, loc);
            db.addAccount(p);
            login.setCurrentUser(p);
            UserEvent e = new UserEvent("add",p);
            notifyAccountObserver(e);
        }
    }

    //creates a guest account
    public void createGuestAccount(String name, String id, String pw, Room loc) throws IOException{
        if(!layout.getRooms().contains(loc)){
            System.out.println("ERROR: No such room in the house");
        }else{
            Profile p = new Guest(name,id,pw, loc);
            db.addAccount(p);
            login.setCurrentUser(p);
            UserEvent e = new UserEvent("add",p);
            notifyAccountObserver(e);
        }
    }

    //creates a guest account
    public void createStrangerAccount(String name, Room loc) throws IOException {
        if(!layout.getRooms().contains(loc)){
            System.out.println("ERROR: No such room in the house");
        }else{
            Profile p = new Stranger(name, loc);
            db.addAccount(p);
            login.setCurrentUser(p);
            UserEvent e = new UserEvent("add",p);
            notifyAccountObserver(e);
        }
    }



    //delete an account
    public void deleteAccount(Profile user) {
        if (db.profileExists(user)) {
            db.deleteAccount(user);
        } else {
            System.out.println("ERROR: The user does not exist");
        }
    }
    

    public void editName(String name){
        login.getCurrentUser().setName(name);
    }

    public void editUserName(String userName){
        if(login.getCurrentUser() instanceof Parent){
            Parent user = (Parent)login.getCurrentUser();
            db.deleteAccount(login.getCurrentUser());
            user.setUserName(userName);
            db.addAccount(user);

        }else if(login.getCurrentUser() instanceof Child){
            Child user = (Child)login.getCurrentUser();
            db.deleteAccount(login.getCurrentUser());
            user.setUserName(userName);
            db.addAccount(user);

        }else{
            Guest user = (Guest)login.getCurrentUser();
            db.deleteAccount(login.getCurrentUser());
            user.setUserName(userName);
            db.addAccount(user);

        }
    }

    public void editPassword(String password){
        if(login.getCurrentUser() instanceof Parent){
            Parent user = (Parent)login.getCurrentUser();
            db.deleteAccount(login.getCurrentUser());
            user.setPassword(password);
            db.addAccount(user);

        }else if(login.getCurrentUser() instanceof Child){
            Child user = (Child)login.getCurrentUser();
            db.deleteAccount(login.getCurrentUser());
            user.setPassword(password);
            db.addAccount(user);

        }else{
            Guest user = (Guest)login.getCurrentUser();
            db.deleteAccount(login.getCurrentUser());
            user.setPassword(password);
            db.addAccount(user);

        }

    }

    public ArrayList<Profile> getProfiles(){
        return db.getProfiles();
    }

    public void uploadTempFile(String csvFilePath){
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                String timestamp = nextLine[0] + "," + nextLine[1];
                String temperatureString = nextLine[2].replace("−", "-");
                double weather = Double.parseDouble(temperatureString);
                weatherData.put(timestamp, weather);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    public void startSimulation() throws IOException {
        clock.start();

        // Run the while loop in a separate thread
        new Thread(() -> {
            while (clock.isRunning().get()) {
                Event tempEvent = new TemperatureEvent("temperature", this); // Create a new event instance inside the loop
                try {
                    notifyTimeObserver(tempEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void stopSimulation(){
        clock.pause();
    }

    public ArrayList<Zone> getZones(){
        return zones;
    }

    public void setZones(ArrayList<Zone> zones) {
        this.zones = zones;
    }

    public void addZone(Zone z){
        zones.add(z);
    }

    public void setZoneTemperature(double temp, Zone z){
        int index = zones.indexOf(z);
        zones.get(index).setTemperature(temp);
    }

    public double getRoomTemp(Room r){
        double temp = 0;
        for(Zone z: zones){
            if(z.getRooms().contains(r)){
                temp = z.getTemperature();
            }
        }
        return temp;
    }
}