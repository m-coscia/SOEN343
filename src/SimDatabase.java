package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * -----------------------------------------------------------
 * INSTRUCTIONS TO SETTING UP THE DATABASE ON YOUR MACHINE:
 * -----------------------------------------------------------
 * 1. Download MySQL for Windows: https://dev.mysql.com/downloads/installer/ (choose the second one)
 * `1.a) Create a user account to write to MySQL. In the code below, the username I chose was soen343 and the password is SOEN343&Pr0ject
 *  1.b) Just choose the general default options
 * 2. Download MySQL Java Connector: https://dev.mysql.com/downloads/connector/j/
 *  2.a) Choose Platform Independent for Operating System
 *  2.b) Download either option
 * 3. Place the .jar file from Step 2 folder into the project dependencies
 * 4. Run the main method below but replace with your user info created in step 1.a)
 * 5. Check that you the plugin "Database Tools and SQL" checked by consulting downloaded plugins (Ctrl+Alt+S)
 * 6. Create new data source
 *  6.a) Navigate to File > New > Data Source > MySQL > MySQL
 *  6.b) In the popup, enter your user info from 1.a) and under database, type in value from databaseName variable (line 32)
 *  6. c) When you type out the command "SHOW DATABASES" in the console for MySQL, you can view the entries in the database
 */
public class SimDatabase {
    /**
     * SimDatabase.main() - set up the database and table for user profiles
     * @param args
     */
    public static void main(String[] args){
        Connection con=null;
        Statement stmt=null;
        String databaseName="Smart_Home_Simulator_DB";
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/mysql", "soen343", "SOEN343&Pr0ject");
            stmt = con.createStatement();
            int status = stmt.executeUpdate("CREATE DATABASE "+databaseName);
            if(status > 0) System.out.println("Database is created successfully !!!");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}