package src.logic;

import src.components.Room;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    Connection con;
    private ArrayList<Profile> profiles = new ArrayList<>();
    private ArrayList<Room> rooms = HouseLayout.getHouseLayout().getRooms();
    private static DataBase db = null;
    private File accountLog;

    private DataBase() {
        //Connection con=null;
        Statement stmt=null;
        try{
            //connecting to database
            con = DriverManager.getConnection("jdbc:mysql://localhost/mysql", "root", "Iilham101$");
            //required for running SQL commands in Java
            stmt = con.createStatement();
            String query = "SELECT * FROM smart_home_simulator_db.FullProfiles";
            //fetch all profiles from SQL database
            ResultSet rs = stmt.executeQuery(query);
            //declaring temp variables
            String type, name, userName, password;
            int locationId;
            boolean window, lights, garage, doors;
            //loop through each entry of SQL table and create profiles
            while(rs.next()) {
                type = rs.getString("Type");
                name = rs.getString("Name");
                userName = rs.getString("Username");
                password = rs.getString("Password");
                locationId =  rs.getInt("Room");
                window = rs.getBoolean("Window");
                lights = rs.getBoolean("Lights");
                garage = rs.getBoolean("Garage");
                doors = rs.getBoolean("Doors");
                Profile profile = null;
                Permissions permissions = new Permissions(window,doors,garage,lights,true);
                // I THINK THE ISSUE IS BCZ THIS IS DONE BEFORE THE HOUSE LAYOUT IS SET SO THE FINDROOM() ALWAYS IS NULL
                Room location = findRoom(locationId);
                ;
                switch(type){
                    case "PARENT":
                        profile = new Parent(name, userName, password, location);
                        profile.setPermissions(permissions);
                        location.addUserToRoom(profile);
                        break;
                    case "CHILD":
                        profile = new Child(name, userName, password, location);
                        profile.setPermissions(permissions);
                        location.addUserToRoom(profile);
                        break;
                    case "GUEST":
                        profile = new Guest(name, userName, password, location);
                        profile.setPermissions(permissions);
                        location.addUserToRoom(profile);
                        break;
                    case "STRANGER":
                        profile = new Stranger(name, location);
                        permissions.setTemperaturePermission(false);
                        profile.setPermissions(permissions);
                        location.addUserToRoom(profile);
                        break;
                }
                // add profile to arraylist
                if (profile != null) {
                    profiles.add(profile);
                }
            }
            //con.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        /*
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
        } */
    }

    public static synchronized DataBase getDataBase(){
        if(db==null){
            db = new DataBase();
        }
        return db;
    }


    public void addAccount(Profile p){
        profiles.add(p);
        //Connection con=null;
        Statement stmt=null;
        String name = p.getName();
        int location = p.getLocation().getId();
        Permissions permission = p.getPermissions();
        try{
            //connecting to database
            //con = DriverManager.getConnection("jdbc:mysql://localhost/mysql", "root", "123");
            //required for running SQL commands in Java
            stmt = con.createStatement();
            String query = "";
            //insert profiles into database
            if(p instanceof Parent){
                String username = (((Parent) p).getUserName());
                String password = (((Parent) p).getPassword());
                //query = "INSERT INTO smart_home_simulator_db.profiles VALUES ('PARENT', '"+name+"', '"+username+"', '"+password+"', "+location+")";
               query = "INSERT INTO smart_home_simulator_db.FullProfiles (Type,Name, Username, Password, Room, `Window`, Lights, Garage, Doors) VALUES " +
                       "('PARENT', \'"+name+"\', \'"+username+"\', '"+password+"', "+location+","
                       + permission.getWindowsPermission() +"," +
                       permission.getLightsPermission() + "," +
                       permission.getGarageDoorPermission() +"," +
                       permission.getDoorsPermission() +
                       ")";
                stmt.executeUpdate(query);
            }
            else if(p instanceof Child){
                String username = (((Child) p).getUserName());
                String password = (((Child) p).getPassword());
                query = "INSERT INTO smart_home_simulator_db.FullProfiles (Type, Name, Username, Password, Room, `Window`, Lights, Garage, Doors) " +
                        "VALUES ('CHILD', '"+name+"', '"+username+"', '"+password+"', "+location+ ","
                        + permission.getWindowsPermission() +"," +
                        permission.getLightsPermission() + "," +
                        permission.getGarageDoorPermission() +"," +
                        permission.getDoorsPermission() +
                        ")";
                stmt.executeUpdate(query);
            }
            else if(p instanceof Guest){
                String username = (((Guest) p).getUserName());
                String password = (((Guest) p).getPassword());
                query = "INSERT INTO smart_home_simulator_db.FullProfiles (Type, Name, Username, Password, Room, `Window`, Lights, Garage, Doors) " +
                        "VALUES ('GUEST', '"+name+"', '"+username+"', '"+password+"', "+location+ ","
                        + permission.getWindowsPermission() +"," +
                        permission.getLightsPermission() + "," +
                        permission.getGarageDoorPermission() +"," +
                        permission.getDoorsPermission() +
                        ")";
                stmt.executeUpdate(query);
            }
            else{
                query = "INSERT INTO Smart_Home_Simulator_db.FullProfiles (Type, Name, Username, Password, Room, `Window`, Lights, Garage, Doors)" +
                        " VALUES ('STRANGER', '"+name+"','','', "+location+ ","
                        + permission.getWindowsPermission() +"," +
                        permission.getLightsPermission() + "," +
                        permission.getGarageDoorPermission() +"," +
                        permission.getDoorsPermission() +
                        ")";
                stmt.executeUpdate(query);
            }
           // con.close();

            //TODO: location? is 0 equivalent to not being in the home?
            /*
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
            fw.close(); */
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        /* catch(IOException e){

            System.out.println(e.getMessage());
        } */
    }

    public void deleteAccount(Profile p){
        profiles.remove(p);

        //remove from SQL table
        Connection con=null;
        Statement stmt=null;
        String name = p.getName();
        int location = p.getLocation().getId();
        String type = p.getClass().getName().toUpperCase();
        String query,username, password;

        try{
            //connecting to database
            con = DriverManager.getConnection("jdbc:mysql://localhost/mysql", "root", "123");
            //required for running SQL commands in Java
            stmt = con.createStatement();
            switch(type){
                case "PARENT":
                    username = (((Parent) p).getUserName());
                    password = (((Parent) p).getPassword());
                    query = "DELETE FROM smart_home_simulator_db.profiles " +
                            "WHERE Type='PARENT' AND " +
                            "Name='"+name+"' AND " +
                            "Username='"+username+"' AND " +
                            "Password='"+password+"' AND " +
                            "Room="+location;
                    stmt.executeUpdate(query);
                    break;
                case "CHILD":
                    username = (((Child) p).getUserName());
                    password = (((Child) p).getPassword());
                    query = "DELETE FROM smart_home_simulator_db.profiles " +
                            "WHERE Type='CHILD' AND " +
                            "Name='"+name+"' AND " +
                            "Username='"+username+"' AND " +
                            "Password='"+password+"' AND " +
                            "Room="+location;
                    stmt.executeUpdate(query);
                    break;
                case "GUEST":
                    username = (((Guest) p).getUserName());
                    password = (((Guest) p).getPassword());
                    query = "DELETE FROM smart_home_simulator_db.profiles " +
                            "WHERE Type='GUEST' AND " +
                            "Name='"+name+"' AND " +
                            "Username='"+username+"' AND " +
                            "Password='"+password+"' AND " +
                            "Room="+location;
                    stmt.executeUpdate(query);
                    break;
                case "STRANGER":
                    query = "DELETE FROM smart_home_simulator_db.profiles " +
                            "WHERE Type='STRANGER' AND " +
                            "Name='"+name+"' AND " +
                            "Room="+location;
                    stmt.executeUpdate(query);
                    break;
            }
            con.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }


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
//        System.out.println("you are in the profileExists() method: " +userProfile.getName());
//        String sql = "SELECT FROM FullProfiles WHERE Type = ? AND Name = \'"+userProfile.getName() +" ";
//
//        try{
//            PreparedStatement pst = con.prepareStatement(sql);
//
//            pst.setString(1, userProfile.getClass().getSimpleName().toUpperCase());
//            //pst.setString(2, userProfile.getName());
//
//
//            try (ResultSet rs = pst.executeQuery()) {
//                if (rs.next()) {
//                    int count = rs.getInt(1);
//                    System.out.println("the user was found and their id is: " + count);
//                    return count > 0;
//                }
//            }
//
//        }catch(SQLException e){
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//
//        return false;
    }    

    public Profile findProfile(String name) {
        for (Profile profile : profiles) {
            if (profile.getName().equalsIgnoreCase(name)) {
                return profile;
            }
        }

//        String query = "SELECT * FROM profiles WHERE Name = ? ";
//
//        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mysql", "root", "123");
//             PreparedStatement pst = con.prepareStatement(query)) {
//
//            pst.setString(1, name);
//
//            try (ResultSet rs = pst.executeQuery()) {
//                if (rs.next()) {
//                    // Retrieve profile attributes from the result set
//                    String type = rs.getString("Type");
//                    String username = rs.getString("Username");
//                    String password = rs.getString("Password");
//                    int room = rs.getInt("Room");
//                    boolean lights = rs.getBoolean("Lights");
//                    boolean garage = rs.getBoolean("Garage");
//                    boolean doors = rs.getBoolean("Doors");
//                    boolean windows = rs.getBoolean("Window");
//
//                    Permissions p = new Permissions(windows,doors,garage,lights,true);
//                    // Create and return a Profile object
//                    switch (type.toUpperCase()) {
//                        case "PARENT":
//                            Parent parent =  new Parent(name, username, password, findRoom(room));
//                            parent.setPermissions(p);
//                            return parent;
//
//                        case "CHILD":
//                           Child child =  new Child(name, username, password,findRoom(room));
//                           child.setPermissions(p);
//                        case "GUEST":
//                            Guest guest = new Guest(name, username, password, findRoom(room));
//                            guest.setPermissions(p);
//                            return guest;
//                        case "STRANGER":
//                            Stranger stranger = new Stranger(name, findRoom(room));
//                            stranger.setPermissions(p);
//                            return stranger;
//                        default:
//                            // Handle unknown profile type
//                            System.err.println("Unknown profile type: " + type);
//                            return null;
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        return null;
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
