package src.Observer;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import src.logic.SimulationParameter;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimeObserver implements Observer{
    private SimulationParameter param;

    public TimeObserver(SimulationParameter param){
        this.param = param;
    }
    @Override
    public void update(Event event) throws IOException {
        String csvFilePath = "july_temp.csv"; // Provide the path to your CSV file

        LocalDate targetDate = ((TemperatureEvent)event).getDate();

        // Create a DateTimeFormatter to parse the date from the CSV file
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // Parse the date from the CSV file
                LocalDate currentDate = LocalDate.parse(nextLine[0], formatter);

                // Check if the current date matches the target date
                if (currentDate.equals(targetDate)) {
                    if(event.getType().equals("Morning_Temp")){
                        String cellValue = nextLine[1];
                        param.setWeatherOutside(Double.parseDouble(cellValue));
                        break;

                    }else if(event.getType().equals("Afternoon_Temp")){
                        String cellValue = nextLine[2];
                        param.setWeatherOutside(Double.parseDouble(cellValue));
                        break;

                    }else if(event.getType().equals("Night_Temp")){
                        String cellValue = nextLine[3];
                        param.setWeatherOutside(Double.parseDouble(cellValue));
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }


}

