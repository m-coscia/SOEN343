package src.logic;

import java.io.*;
import java.util.ArrayList;

public class DataBase {
    private ArrayList<Profile> profiles = new ArrayList<Profile>();
    private static DataBase db = null;
    private File accountLog;

    private DataBase() {
//        accountLog = new File("accountLog.txt");
//        BufferedReader reader = new BufferedReader(new FileReader(accountLog));
//        String line;
//        while ((line = reader.readLine()) != null) {
//            // Split the line by a delimiter, e.g., comma
//            String[] parts = line.split(",");
//
//            // Assuming each line has two attributes
//            String type = parts[0].trim();
//            String name = parts[1].trim();
//            String userName = parts[2].trim();
//            String password = parts[3].trim();
//            String location = parts[4].trim();
//
//            Profile user = new Profile(name, location);
//
//            switch(type){
//                case "PARENT":
//
//                    break;
//                case "CHILD":
//                    break;
//                case "GUEST":
//                    Guest user = new Guest
//                    break;
//                case "STRANGER":
//                    Stranger user = new Stranger(name,location);
//                    break;
//            }
//
//            }
//
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
