package src;


import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws FileNotFoundException {
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

        System.out.println("Enter another layout file:");
        String layoutFile2 = myObj.nextLine();  // Read user input

        layout.setHouseLayout(layoutFile2);

        for(int i=0;i<layout.getRooms().size();i++){
            System.out.println("Room type:" + layout.getRooms().get(i).getType());
            System.out.println("Number of lights:" + layout.getRooms().get(i).getNumLights());
            System.out.println("Number of windows:" + layout.getRooms().get(i).getNumWindows());
            System.out.println("Number of doors:" + layout.getRooms().get(i).getNumDoors());

        }

    }
}