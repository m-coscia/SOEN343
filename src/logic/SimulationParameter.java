package src.logic;

import src.Observer.Event;
import src.Observer.Observer;
import src.Observer.UserEvent;
import src.components.Clock;
import src.components.Room;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class SimulationParameter {
    private HouseLayout layout = HouseLayout.getHouseLayout();
    private DataBase db = DataBase.getDataBase();
    private LocalDate date;
    private LocalTime time;
    private Login login;
    private double weatherInside;
    private double weatherOutside;
    private Observer observer;
    private Clock clock;



    public SimulationParameter(String layoutFile, LocalDate d, LocalTime t, double inside, double outside, Login loggedIn) throws FileNotFoundException {
        layout.setHouseLayout(layoutFile);
        date = d;
        clock = new Clock();
        clock.setTime(t);
        weatherInside = inside;
        weatherOutside = outside;
        login = loggedIn;
        db.setRooms(layout.getRooms());
    }

    public void notifyObserver(Event e) throws IOException {
        observer.update(e);
    }

    public void attachObserver(Observer o){
        observer=o;
    }

    public HouseLayout getLayout(){
        return layout;
    }

    public LocalTime getTime(){
        return time;
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
    public void createParentAccount(String name, String id, String pw, Room loc) throws IOException {
        if(!layout.getRooms().contains(loc)){
            System.out.println("ERROR: No such room in the house");
        }else {
            Profile p = new Parent(name, id, pw, loc);
            db.addAccount(p);
            login.setCurrentUser(p);
            UserEvent e = new UserEvent("add",p);
            notifyObserver(e);
        }
    }

    //creates a child account
    public void createChildAccount(String name, String id, String pw, Room loc) throws IOException {
        if(!layout.getRooms().contains(loc)){
            System.out.println("ERROR: No such room in the house");
        }else {
            Profile p = new Child(name, id, pw, loc);
            db.addAccount(p);
            login.setCurrentUser(p);
            UserEvent e = new UserEvent("add",p);
            notifyObserver(e);
        }
    }

    //creates a guest account
    public void createGuestAccount(String name, String id, String pw, Room loc) throws IOException {
        if(!layout.getRooms().contains(loc)){
            System.out.println("ERROR: No such room in the house");
        }else{
            Profile p = new Guest(name,id,pw, loc);
            db.addAccount(p);
            login.setCurrentUser(p);
            UserEvent e = new UserEvent("add",p);
            notifyObserver(e);
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
            notifyObserver(e);
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
//            login.setCurrentUser(user);

        }else if(login.getCurrentUser() instanceof Child){
            Child user = (Child)login.getCurrentUser();
            db.deleteAccount(login.getCurrentUser());
            user.setUserName(userName);
            db.addAccount(user);
//            login.setCurrentUser(user);
        }else{
            Guest user = (Guest)login.getCurrentUser();
            db.deleteAccount(login.getCurrentUser());
            user.setUserName(userName);
            db.addAccount(user);
//            login.setCurrentUser(user);
        }
    }

    public void editPassword(String password){
        if(login.getCurrentUser() instanceof Parent){
            Parent user = (Parent)login.getCurrentUser();
            db.deleteAccount(login.getCurrentUser());
            user.setPassword(password);
            db.addAccount(user);
//            login.setCurrentUser(user);

        }else if(login.getCurrentUser() instanceof Child){
            Child user = (Child)login.getCurrentUser();
            db.deleteAccount(login.getCurrentUser());
            user.setPassword(password);
            db.addAccount(user);
//            login.setCurrentUser(user);
        }else{
            Guest user = (Guest)login.getCurrentUser();
            db.deleteAccount(login.getCurrentUser());
            user.setPassword(password);
            db.addAccount(user);
//            login.setCurrentUser(user);
        }

    }

    public ArrayList<Profile> getProfiles(){
        return db.getProfiles();
    }


}
