package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import src.components.Room;
import src.components.RoomType;

public class HouseLayout {
    private  File layoutFile;
    private ArrayList<Room> rooms = new ArrayList<Room>();
    private static HouseLayout houseLayout = null;

    private HouseLayout(){

    }

    public static synchronized HouseLayout getHouseLayout(){
        if(houseLayout==null){
            houseLayout = new HouseLayout();
        }
        return houseLayout;
    }

    public ArrayList<Room> getRooms(){
        return rooms;
    }

    public void setHouseLayout(String fileName) throws FileNotFoundException {
        rooms.clear();
        layoutFile = new File(fileName);
        Scanner read = new Scanner(layoutFile);
        RoomType type = null;
        int lights = 0, windows = 0, doors = 0;
        int count=0;
        while (read.hasNextLine()){
            switch(count){
                case 0:
                    read.nextLine();
                    count++;
                    break;
                case 1:
                    type = RoomType.valueOf(read.nextLine());
                    count++;
                    break;
                case 2:
                    lights = Integer.parseInt(read.nextLine());
                    count++;
                    break;
                case 3:
                    windows = Integer.parseInt(read.nextLine());
                    count++;
                    break;
                case 4:
                    doors = Integer.parseInt(read.nextLine());
                    count++;
                    break;
                case 5:
                    read.nextLine();
                    count=0;
                    Room newRoom = new Room(type,windows,lights,doors);
                    rooms.add(newRoom);
                    break;
            }
        }

    }


}
