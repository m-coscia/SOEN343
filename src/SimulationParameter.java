package src;

import src.components.Room;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class SimulationParameter {
    private HouseLayout layout;
    private DataBase db;
    private LocalDate date;
    private LocalTime time;
    private Login login;
    private double weatherInside;
    private double weatherOutside;
    private ArrayList<virtualTenant> tenants = new ArrayList<virtualTenant>();

    public SimulationParameter(String layoutFile, LocalDate d, LocalTime t, double inside, double outside) throws FileNotFoundException {
        HouseLayout layout = HouseLayout.getHouseLayout();
        layout.setHouseLayout(layoutFile);
        date = d;
        time = t;
        weatherInside = inside;
        weatherOutside = outside;
    }

    public void login(Profile user){
        login = new Login(user);
    }

    public void createParentAccount(String name, String id, String pw){
        Profile p = new Parent(name,id,pw);
        db.addAccount(p);
        login.setCurrentUser(p);
        setVirtualTenants(p,null);
    }

    public void createChildAccount(String name, String id, String pw){
        Profile p = new Child(name,id,pw);
        db.addAccount(p);
        login.setCurrentUser(p);
        setVirtualTenants(p,null);
    }

    public void createGuestAccount(String name, String id, String pw){
        Profile p = new Guest(name,id,pw);
        db.addAccount(p);
        login.setCurrentUser(p);
        setVirtualTenants(p,null);
    }


    public void deleteAccount(Profile user){
        if(db.findProfile(user)){
            db.deleteAccount(user);
        }
        //finds profile in tenants list and removes it
        for(int i=0;i<tenants.size();i++){
            if(tenants.get(i).getProfile() == user){
                tenants.remove(tenants.get(i));
            }
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
            login.setCurrentUser(user);

        }else if(login.getCurrentUser() instanceof Child){
            Child user = (Child)login.getCurrentUser();
            db.deleteAccount(login.getCurrentUser());
            user.setUserName(userName);
            db.addAccount(user);
            login.setCurrentUser(user);
        }else{
            Guest user = (Guest)login.getCurrentUser();
            db.deleteAccount(login.getCurrentUser());
            user.setUserName(userName);
            db.addAccount(user);
            login.setCurrentUser(user);
        }
    }

    public void editPassword(String password){
        if(login.getCurrentUser() instanceof Parent){
            Parent user = (Parent)login.getCurrentUser();
            db.deleteAccount(login.getCurrentUser());
            user.setPassword(password);
            db.addAccount(user);
            login.setCurrentUser(user);

        }else if(login.getCurrentUser() instanceof Child){
            Child user = (Child)login.getCurrentUser();
            db.deleteAccount(login.getCurrentUser());
            user.setPassword(password);
            db.addAccount(user);
            login.setCurrentUser(user);
        }else{
            Guest user = (Guest)login.getCurrentUser();
            db.deleteAccount(login.getCurrentUser());
            user.setPassword(password);
            db.addAccount(user);
            login.setCurrentUser(user);
        }

    }

    public void changeDate(){

    }

    public void changeTime(){

    }

    //returns the list of virtual tenants
    public ArrayList<virtualTenant> getTenants() {
        return tenants;
    }

    //creates virtual tenants with a profile and location and adds it to the list of virtual tenants
    public void setVirtualTenants(Profile p, Room loc){
        virtualTenant tenant = new virtualTenant(p, loc);
        tenants.add(tenant);
    }


}
