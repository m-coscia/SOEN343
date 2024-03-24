package Observer;

import logic.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddAccountObserver implements Observer {
    private File accountLog;

    public AddAccountObserver() {
        // Determine a writable location, e.g., user's home directory
        String userHomeDir = System.getProperty("user.home");
        File writableDir = new File(userHomeDir, "MyAppData");
        if (!writableDir.exists()) {
            writableDir.mkdirs(); // Create the directory if it doesn't exist
        }
        accountLog = new File(writableDir, "accountLog.txt");

        if (!accountLog.exists()) {
            try {
                accountLog.createNewFile(); // Create the file if it doesn't exist
            } catch (IOException e) {
                System.err.println("Failed to create accountLog.txt: " + e.getMessage());
            }
        }
    }

    @Override
    public void update(Profile user) throws IOException{
        try (FileWriter writer = new FileWriter(accountLog, true)) {
            // Compose text to write to the file
            String profileInfo = composeProfileInfo(user);

            // Write the composed text to the file
            writer.write(profileInfo);

            System.out.println("Profile has been added to the file successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    private String composeProfileInfo(Profile user) {
        // Compose the profile information string based on the user instance type
        StringBuilder profileInfo = new StringBuilder();
        if(user instanceof Parent){
            profileInfo.append("PARENT,");
            profileInfo.append(user.getName()).append(",");
            profileInfo.append(((Parent) user).getUserName()).append(",");
            profileInfo.append(((Parent) user).getPassword()).append(",");
            profileInfo.append(user.getLocation().getId()).append("\n");
        } else if(user instanceof Child){
            profileInfo.append("CHILD,");
            profileInfo.append(user.getName()).append(",");
            profileInfo.append(((Child) user).getUserName()).append(",");
            profileInfo.append(((Child) user).getPassword()).append(",");
            profileInfo.append(user.getLocation().getId()).append("\n");
        } else if(user instanceof Guest){
            profileInfo.append("GUEST,");
            profileInfo.append(user.getName()).append(",");
            profileInfo.append(((Guest) user).getUserName()).append(",");
            profileInfo.append(((Guest) user).getPassword()).append(",");
            profileInfo.append(user.getLocation().getId()).append("\n");
        } else { // Assumes default case is STRANGER
            profileInfo.append("STRANGER,");
            profileInfo.append(user.getName()).append(",");
            profileInfo.append(user.getLocation().getId()).append("\n");
        }
        return profileInfo.toString();
    }
}
