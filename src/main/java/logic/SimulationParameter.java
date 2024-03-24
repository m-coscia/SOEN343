package src.main.java.logic;

import src.main.java.components.Room;
import src.main.java.Observer.Observer;

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

    public SimulationParameter(String layoutFile, LocalDate d, LocalTime t, double inside, double outside, Login loggedIn) throws FileNotFoundException {
        layout.setHouseLayout(layoutFile);
        date = d;
        time = t;
        weatherInside = inside;
        weatherOutside = outside;
        login = loggedIn;
        db.setRooms(layout.getRooms());
    }

    public void notifyObserver(Profile user) throws IOException {
        observer.update(user);
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
        time = t;
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
            notifyObserver(p);
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
            notifyObserver(p);
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
            notifyObserver(p);
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
            notifyObserver(p);
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
