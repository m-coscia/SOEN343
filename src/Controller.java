package src;
import src.components.Room;
import src.components.RoomType;
import src.logic.*;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.*;

public class Controller {

    private DataBase database;
    public Controller(){
        database = DataBase.getDataBase();
    }

    public void layoutSetUp(String filename) throws FileNotFoundException {
        HouseLayout.getHouseLayout().setHouseLayout(filename);
    }

    public void saveProfiles(ArrayList<JTextField> nameFields, ArrayList<JTextField> usernameFields,
                             ArrayList<JPasswordField> passwordFields, ArrayList<JComboBox<String>> typeFields, ArrayList<JCheckBox> windowsCheckboxes,
                             ArrayList<JCheckBox> doorsCheckboxes, ArrayList<JCheckBox> garageCheckboxes,
                             ArrayList<JCheckBox> lightsCheckboxes, ArrayList<JComboBox<String>> locationFields) {

        for (int i = 0; i < nameFields.size(); i++){

            ArrayList<Room> rooms = HouseLayout.getHouseLayout().getRooms();
            Room room = null;
            for (Room r: rooms){
                if(String.valueOf(r.getType()).equals(locationFields.get(i).getSelectedItem())){
                    System.out.println(locationFields.get(i).getSelectedItem());
                    room = r;
                    System.out.println(Arrays.toString(r.getUsers()));
                    break;
                }

            }

//            if(locationFields.get(i).getSelectedItem().equals("BEDROOM")){
//
//            }else if (locationFields.get(i).getSelectedItem().equals("LIVING ROOM")){
//                //r = RoomType.LIVINGROOM;
//            } else if (locationFields.get(i).getSelectedItem().equals("BATHROOM")) {
//               // r = RoomType.BATHROOM;
//            } else if (locationFields.get(i).getSelectedItem().equals("KITCHEN")) {
//                //r = RoomType.KITCHEN;
//            }else if (locationFields.get(i).getSelectedItem().equals("GARAGE")) {
//                //r = RoomType.GARAGE;
//            }
            boolean window = windowsCheckboxes.get(i).isSelected();
            boolean door = doorsCheckboxes.get(i).isSelected();
            boolean garage = garageCheckboxes.get(i).isSelected();
            boolean lights = lightsCheckboxes.get(i).isSelected();

            Profile p = null;
            if (typeFields.get(i).getSelectedItem().equals("Parent")){

                p = new Parent(nameFields.get(i).getText(),
                        usernameFields.get(i).getText(),
                        passwordFields.get(i).getText(), room);

            }else if (typeFields.get(i).getSelectedItem().equals("Child")){

                p = new Child(nameFields.get(i).getText(),
                        usernameFields.get(i).getText(),
                        passwordFields.get(i).getText(),room );

            }else if (typeFields.get(i).getSelectedItem().equals("Guest")){

                p = new Guest(nameFields.get(i).getText(),
                        usernameFields.get(i).getText(),
                        passwordFields.get(i).getText(),room );

            }else if (typeFields.get(i).getSelectedItem().equals("Stranger")){

                p = new Stranger(nameFields.get(i).getText(), room);
            }
            Permissions permission = new Permissions(window,door,garage,lights, false);

            if(p != null){
                p.setPermissions(permission);
                database.addAccount(p);
                System.out.println( typeFields.get(i).getSelectedItem()+ " profile with name "+ p.getName()+ " was added to the database.");
            }else {
                System.out.println("Could not add profile with name " +nameFields.get(i).getText() );
            }


        }
    }

    public ArrayList<Profile> getProfiles(){
        return database.getProfiles();
    }

    public double getTemperature(){
        return 14.3;
    }

    public void login(Profile p){
        Login l = new Login(p);
//        if(p instanceof Parent){
//            if(((Parent) p).getUserName().equals(username) && ((Parent) p).getPassword().equals(password)){
//                 l = new Login(p);
//            }
//        } else if (p instanceof Child) {
//            if(((Child) p).getUserName().equals(username) && ((Child) p).getPassword().equals(password)){
//                l = new Login(p);
//            }
//        }else if (p instanceof Guest){
//            if(((Guest) p).getUserName().equals(username) && ((Guest) p).getPassword().equals(password)){
//                l = new Login(p);
//            }
//        }else {
//            l = new Login(p);
//        }
    }

    public String getType(Profile profile) {
        if( profile instanceof Parent){
            return "Parent";
        }else if (profile instanceof Guest){
            return "Guest";
        }else if(profile instanceof Child){
            return "Child";
        }else{
            return "Stranger";
        }
    }

    public String getLocation(Profile profile){

//        Room r = profile.getLocation();
//        return String.valueOf(r.getType());
        return "FIX LOCATION";
    }
//    public String[] getExistingLocations(){
//        String[] rooms = new String[]{String.valueOf(RoomType.BEDROOM)};
//
//        return rooms;
//    }
}
