package src.logic;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws FileNotFoundException {

        //new firstFrame();
        HouseLayout layout = HouseLayout.getHouseLayout();
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        System.out.println("Enter layout file:");
        String layoutFile = myObj.nextLine();  // Read user input

        layout.setHouseLayout(layoutFile);

        for(int i=0;i<layout.getRooms().size();i++){
            System.out.println("Room type:" + layout.getRooms().get(i).getType());
            System.out.println("Number of lights:" + layout.getRooms().get(i).getNumLights());
            System.out.println("Number of windows:" + layout.getRooms().get(i).getNumWindows());
            System.out.println("Number of doors:" + layout.getRooms().get(i).getNumDoors());
        }

        LocalDate date = LocalDate.of(2000, 5, 15);
        LocalTime time = LocalTime.of(14, 30, 0);


        System.out.println("The simulation parameter have been set \n" +
                "The layout is: ");
        for(int i=0;i<layout.getRooms().size();i++){
            System.out.println("Room type:" + layout.getRooms().get(i).getType());
            System.out.println("Number of lights:" + layout.getRooms().get(i).getNumLights());
            System.out.println("Number of windows:" + layout.getRooms().get(i).getNumWindows());
            System.out.println("Number of doors:" + layout.getRooms().get(i).getNumDoors());
        }

        Login loggedIn = new Login(null);
        DataBase db = DataBase.getDataBase();
        SimulationParameter param = new SimulationParameter(layoutFile, date, time, 21.0, 12.4, loggedIn);

        System.out.println("The date is: " + param.getDate());
        System.out.println("The time is: " + param.getTime());
        System.out.println("The inside temperature is: " + param.getWeatherInside());
        System.out.println("The outside temperature is: " + param.getWeatherOutside());

        param.createParentAccount("Naika", "naikiki", "kiki2002",layout.getRooms().get(0));
        param.createChildAccount("Yasmine", "yaya", "yaya2002",layout.getRooms().get(1));
        param.createGuestAccount("Asmae", "asmama", "asmou2002",layout.getRooms().get(0));

        System.out.println("The currently logged in user is: " + param.getLoggedIn().getName() +
                "\n And the user is located in a: " + param.getLoggedIn().getLocation().getType());

        param.login(param.getProfiles().get(1));

        System.out.println("The currently logged in user is: " + param.getLoggedIn().getName() +
                "\n And the user is located in a: " + param.getLoggedIn().getLocation().getType());



    }
}