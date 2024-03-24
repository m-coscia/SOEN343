package src.main.java.Observer;

import src.main.java.logic.Profile;

import java.io.*;

public class RemoveAccountObserver implements Observer {
    private File accountLog = new File("accountLog.txt");

    @Override
    public void update(Profile user) throws IOException {
//        String fileContent="";
//        File tempFile = new File("temp.txt");
//        BufferedReader reader = new BufferedReader(new FileReader(accountLog));
//        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
//        String currentLine;
//        int lineNumber = 1;
//
//        while ((currentLine = reader.readLine()) != null) {
//            // If the current line is not the one to be removed, write it to the temp file
//            lineNumber++;
//        }
//        writer.close();
//        reader.close();
//
//        // Delete the original file
//        if (!inputFile.delete()) {
//            System.out.println("Could not delete the original file.");
//            return;
//        }
//
//        // Rename the temp file to the original file name
//        if (!tempFile.renameTo(inputFile)) {
//            System.out.println("Could not rename the temp file.");
//        }
//
//        System.out.println("Line removed successfully.");
//}
//
//
//    String[] parts = input.split(",");
//



    }
}
