package logic;

import components.Room;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class DataBase {
    private ArrayList<Profile> profiles = new ArrayList<>();
    private ArrayList<Room> rooms = HouseLayout.getHouseLayout().getRooms();
    private static DataBase db = null;
    private File accountLog;

    private DataBase() {
        try {
            accountLog = new File("accountLog.txt");
            BufferedReader reader = new BufferedReader(new FileReader(accountLog));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0].trim();
                String name = parts[1].trim();
                String userName, password;
                int locationId;
                Profile profile = null;
                Room location;
    
                switch (type) {
                    case "PARENT":
                        userName = parts[2].trim();
                        password = parts[3].trim();
                        locationId = Integer.parseInt(parts[4].trim());
                        location = findRoom(locationId);
                        profile = new Parent(name, userName, password, location);
                        break;

                    case "CHILD":
                        userName = parts[2].trim();
                        password = parts[3].trim();
                        locationId = Integer.parseInt(parts[4].trim());
                        location = findRoom(locationId);
                        profile = new Child(name, userName, password, location);
                        break;

                    case "GUEST":
                        userName = parts[2].trim();
                        password = parts[3].trim();
                        locationId = Integer.parseInt(parts[4].trim());
                        location = findRoom(locationId);
                        profile = new Guest(name, userName, password, location);
                        break;

                    case "STRANGER":
                        locationId = Integer.parseInt(parts[2].trim());
                        location = findRoom(locationId);
                        profile = new Stranger(name, location);
                        break;

                }
    
                if (profile != null) {
                    profiles.add(profile);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static synchronized DataBase getDataBase(){
        if(db==null){
            db = new DataBase();
        }
        return db;
    }

    public void addAccount(Profile p){
        profiles.add(p);
        try{

            //TODO: location? is 0 equivalent to not being in the home?
            FileWriter fw = new FileWriter(accountLog,true);
            if( p instanceof  Parent){
                fw.write("PARENT," + p.getName() +"," + ((Parent) p).getUserName() + "," + ((Parent) p).getPassword() + "," +((Parent) p).getLocation().getId()+ "\n" );
            }else if (p instanceof Child){
                fw.write("CHILD," + p.getName() +"," + ((Child) p).getUserName() + "," + ((Child) p).getPassword() +  "," +((Child) p).getLocation().getId()+ "\n" );
            }else if (p instanceof  Guest){
                fw.write("GUEST," + p.getName() +"," + ((Guest) p).getUserName() + "," + ((Guest) p).getPassword() + "," +((Guest) p).getLocation().getId()+ "\n" );
            }else if(p instanceof Stranger){
                fw.write("STRANGER," + p.getName() + "," +((Stranger) p).getLocation().getId()+ "\n" );
            }

            fw.close();
        }catch(IOException e){

            System.out.println(e.getMessage());
        }

    }

    public void deleteAccount(Profile p){
        profiles.remove(p);
    }

    public void addRoom(Room r){
        rooms.add(r);
    }

    public void deleteRoom(Room r){
        rooms.remove(r);
    }

    public void setRooms(ArrayList<Room> r){
        rooms = r;
    }

    public ArrayList<Room> getRooms(){
        return rooms;
    }

    public boolean profileExists(Profile userProfile) {
        for (Profile profile : profiles) {
            if (profile.equals(userProfile)) {
                return true;
            }
        }
        return false;
    }    

    public Profile findProfile(String name) {
        for (Profile profile : profiles) {
            if (profile.getName().equalsIgnoreCase(name)) {
                return profile;
            }
        }
        return null; // Return null if no matching profile is found
    }
    
    public Room findRoom(int identifier) {
        for (Room room : rooms) {
            if (room.getId() == identifier) {
                return room;
            }
        }
        return null; // Return null if no matching room is found
    }
    

    public ArrayList<Profile> getProfiles(){
        return profiles;
    }

    public void printAllProfiles() {
        System.out.println("Profiles in the Database:");
        for (Profile profile : profiles) {
            System.out.println(profile);
        }
    }
    
    public void printAllRooms() {
        System.out.println("Rooms in the Database:");
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

}
