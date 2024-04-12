package src.logic;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import src.Observer.*;
import src.Observer.Events.ActionEvent;
import src.Observer.Events.Event;
import src.Observer.Events.UserEvent;
import src.components.Clock;
import src.components.Room;
import src.components.AC;
import src.components.Heating;
import src.components.Zone;
import src.state.SHP;
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
    private TemperatureObserver temperatureObserver;
    private ActionObserver actionObserver;
    private AC cooler;
    private Heating heater;
    private Clock clock;
    private LocalTime t;
    private Map<String, Double> weatherData = new HashMap<>();
    private ArrayList<Zone> zones = new ArrayList<>();
    private SHP shpContext;

    //is not supposed to take any weather, the weather outside is the same as the weather inside when starting the simulation
    public SimulationParameter(String layoutFile, String tempFile, LocalDate d, LocalTime t, double inside, double outside, Login loggedIn) throws FileNotFoundException {
        //layout.setHouseLayout(layoutFile);
        clock = new Clock();
        clock.setTime(t);
        clock.setDate(d);
        weatherInside = inside;
        weatherOutside = outside;
        login = loggedIn;
        db.setRooms(layout.getRooms());
        uploadTempFile(tempFile);
    }

    public void setShpContext(SHP shpContext) {
        this.shpContext = shpContext;
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

    public void notifyTemperatureObserver(Event e) throws IOException {
        temperatureObserver.update(e);
    }


    public void notifyActionObserver(Event e) throws IOException {
        actionObserver.update(e);
    }

    public void attachAccountObserver(AccountObserver o){
        accountObserver=o;
    }
  
    public void attachTimeObserver(TimeObserver o){
        timeObserver=o;
    }

    public void attachTemperatureObserver(TemperatureObserver o){
        temperatureObserver=o;
    }

    public void attachActionObserver(ActionObserver o){
        actionObserver=o;
    }

    public HouseLayout getLayout(){
        return layout;
    }

    public LocalTime getTime(){
        return clock.getTime();
    }

    public LocalDate getDate(){
        return clock.getDate();
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

    public void setWeatherInside(double temp){
        weatherInside = temp;
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

    public ArrayList<Room> getRooms(){
        return db.getRooms();
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
            System.out.println(e.getMessage());
        }
    }

    public Heating getHeater() {
        return heater;
    }

    public void setHeater(Heating heater) {
        this.heater = heater;
    }

    public ArrayList<Zone> getZones(){
        return zones;
    }

    public void setZones(ArrayList<Zone> zones) throws IOException {

        String action = "Timestamp: " + getDate() + " " + getTime() + "\n" +
                "Event type: Set Zones for SHH \n" +
                "Event description: " + zones.size() + " zones have been created \n" +
                "Details: \n";
                for(Zone z: zones){
                    action += "Zone " + zones.indexOf(z) + ": ";
                    for(Room r:z.getRooms()){
                        action += r.toString() + "\n";
                    }
                    action += "Temperature of the zone: " + z.getTemperature() +"\n";
                }
        ActionEvent actionEvent = new ActionEvent("SHH", action);
        notifyActionObserver(actionEvent);
        this.zones = zones;
    }

    public void addZone(Zone z) throws IOException {
        zones.add(z);
        String action = "Timestamp: " + getDate() + " " + getTime() + "\n" +
                "Event type: Add Zone \n" +
                "Module: SHH \n" +
                "Event description: A zone has been added \n" +
                "Details: \n";
            action += "Zone " + zones.indexOf(z) + ": ";
            for(Room r:z.getRooms()){
                action += r.toString() + "\n";
            }
            action += "Temperature of the zone: " + z.getTemperature() +"\n";
        ActionEvent actionEvent = new ActionEvent("SHH", action);
        notifyActionObserver(actionEvent);
    }

    public void setZoneTemperature(double temp, Zone z) throws IOException {
        int index = zones.indexOf(z);
        String action = "Timestamp: " + getDate() + " " + getTime() + "\n" +
                "Event type: Change Zone Temperature \n"+
                "Module: SHH \n" ;

        if(temp > z.getTemperature()){
            action += "Event description: The temperature of the Zone has increased \n";
        }else{
            action += "Event description: The temperature of the Zone has decreased \n";
        }

        action += "Details: \n";

        action += "Zone " + zones.indexOf(z) + "\n";
        action += "Previous temperature: " + z.getTemperature()+"\n";
        zones.get(index).setTemperature(temp);
        action += "New temperature: " + z.getTemperature()+"\n";
        ActionEvent actionEvent = new ActionEvent("SHH", action);
        notifyActionObserver(actionEvent);
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

    public AC getCooler() {
        return cooler;
    }

    public void setCooler(AC cooler) {
        this.cooler = cooler;
    }

    public void changeInWeather(){
//        if(this.getWeatherOutside() < this.getWeatherInside()){
//            Event temp1 = new TempEvent("ShutdownAC", this);
//            Event temp2 = new TempEvent("OpenWindows", this);
//
//            try{
//                notifyTemperatureObserver(temp1);
//                notifyTemperatureObserver(temp2);
//            }
//            catch ( IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void setZoneType(Zone zone, String type) {
        zone.setType(type);
    }

    public SHP getSHP() {
        return this.shpContext;
    }

    public Map<String, Double> getWeatherData() {
        return this.weatherData;
    }
}
