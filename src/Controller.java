package src;


import src.logic.*;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

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
                             ArrayList<JCheckBox> lightsCheckboxes) {

        for (int i = 0; i < nameFields.size(); i++){
            boolean window = windowsCheckboxes.get(i).isSelected();
            boolean door = doorsCheckboxes.get(i).isSelected();
            boolean garage = garageCheckboxes.get(i).isSelected();
            boolean lights = lightsCheckboxes.get(i).isSelected();

            Profile p = null;
            if (typeFields.get(i).getSelectedItem().equals("Parent")){
                p = new Parent(nameFields.get(i).getText(),
                        usernameFields.get(i).getText(),
                        passwordFields.get(i).getPassword().toString(),null );
            }
            Permissions permission = new Permissions(window,door,garage,lights);

            if(p != null){
                p.setPermissions(permission);
                database.addAccount(p);
            }else {
                System.out.println("Could not add profile with name " +nameFields.get(i).getText() );
            }

        }
    }
}
