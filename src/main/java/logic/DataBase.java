package logic;

import components.Room;

import java.io.*;
import java.util.ArrayList;

public class DataBase {
    private ArrayList<Profile> profiles = new ArrayList<>();
    private ArrayList<Room> rooms = new ArrayList<>();
    private static DataBase db = null;
    private File accountLog;

    private DataBase() {
        initializeAccountLog();
        loadProfiles();
    }

    private void initializeAccountLog() {
        String userHomeDir = System.getProperty("user.home");
        File writableDir = new File(userHomeDir, "MyAppData");
        if (!writableDir.exists()) {
            writableDir.mkdirs();
        }
        accountLog = new File(writableDir, "accountLog.txt");

        if (!accountLog.exists()) {
            try {
                System.out.println("accountLog.txt file not found in resources, creating a new one.");
                accountLog.createNewFile(); // Create a new file if it doesn't exist in resources
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadProfiles() {
        try (BufferedReader reader = new BufferedReader(new FileReader(accountLog))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parseProfileLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseProfileLine(String line) {
        String[] parts = line.split(",");
        String type = parts[0].trim();
        String name = parts[1].trim();
        String userName, password;
        int locationId;
        Profile profile = null;

        switch (type) {
            case "PARENT":
            case "CHILD":
            case "GUEST":
                userName = parts[2].trim();
                password = parts[3].trim();
                locationId = Integer.parseInt(parts[4].trim());
                Room location = findRoom(locationId);
                if (type.equals("PARENT")) {
                    profile = new Parent(name, userName, password, location);
                } else if (type.equals("CHILD")) {
                    profile = new Child(name, userName, password, location);
                } else { // GUEST
                    profile = new Guest(name, userName, password, location);
                }
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

    public static synchronized DataBase getDataBase() {
        if (db == null) {
            db = new DataBase();
        }
        return db;
    }

    public void addAccount(Profile p) {
        profiles.add(p);
        try (FileWriter fw = new FileWriter(accountLog, true)) {
            BufferedWriter bw = new BufferedWriter(fw);
            if (p instanceof Parent) {
                bw.write("PARENT," + p.getName() + "," + ((Parent) p).getUserName() + "," + ((Parent) p).getPassword() + ",0\n");
            } else if (p instanceof Child) {
                bw.write("CHILD," + p.getName() + "," + ((Child) p).getUserName() + "," + ((Child) p).getPassword() + ",0\n");
            } else if (p instanceof Guest) {
                bw.write("GUEST," + p.getName() + "," + ((Guest) p).getUserName() + "," + ((Guest) p).getPassword() + ",0\n");
            } else if (p instanceof Stranger) {
                bw.write("STRANGER," + p.getName() + ",0\n");
            }
            bw.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAccount(Profile p) {
        profiles.remove(p);
    }

    public void addRoom(Room r) {
        rooms.add(r);
    }

    public void deleteRoom(Room r) {
        rooms.remove(r);
    }

    public void setRooms(ArrayList<Room> r) {
        rooms = r;
    }

    public ArrayList<Room> getRooms() {
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

    public ArrayList<Profile> getProfiles() {
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
