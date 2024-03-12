package src;

import java.util.ArrayList;

public class DataBase {
    private ArrayList<Profile> profiles = new ArrayList<Profile>();
    private static DataBase db = null;

    private DataBase(){

    }

    public static synchronized DataBase getDataBase(){
        if(db==null){
            db = new DataBase();
        }
        return db;
    }

    public void addAccount(Profile p){
        profiles.add(p);
    }

    public void deleteAccount(Profile p){
        profiles.remove(p);
    }

    public boolean findProfile(Profile p){
        if(profiles.contains(p)){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<Profile> getProfiles(){
        return profiles;
    }


}
