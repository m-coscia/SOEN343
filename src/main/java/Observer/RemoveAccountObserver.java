package Observer;

import logic.Profile;

import java.io.*;

public class RemoveAccountObserver implements Observer {
    private File writableFile;
    
    public RemoveAccountObserver() {
        // Determine a writable location (for example, in the user's home directory)
        String userHomeDir = System.getProperty("user.home");
        File writableDir = new File(userHomeDir, "MyAppData");
        if (!writableDir.exists()) {
            writableDir.mkdirs(); // Create the directory if it doesn't exist
        }
        writableFile = new File(writableDir, "accountLog.txt");
        
        // Copy the file from resources to the writable location if it doesn't already exist
        if (!writableFile.exists()) {
            try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("accountLog.txt");
                 OutputStream os = new FileOutputStream(writableFile)) {
                if (is == null) {
                    throw new IllegalArgumentException("accountLog.txt file not found in resources!");
                }
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace(); // Handle exceptions appropriately
            }
        }
    }

    @Override
    public void update(Profile user) throws IOException {
        // Modify the file as needed, using writableFile
        // Example: Reading from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(writableFile))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                // Process each line...
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        // Further modification logic here
    }
}