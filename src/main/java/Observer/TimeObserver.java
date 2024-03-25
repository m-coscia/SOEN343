package Observer;


import logic.SimulationParameter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class TimeObserver implements Observer{

    @Override
    public void update(Event event){
        System.out.println("time observer notified!");
        SimulationParameter param = ((TemperatureEvent)event).getParam();
        LocalTime currentTime = param.getTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        String formattedTime = currentTime.format(formatter);
        String timestamp = param.getDate().toString() + "," + formattedTime;
        if (param.getWeatherData().containsKey(timestamp)) {
            double currentWeather = (double) param.getWeatherData().get(timestamp);
            param.setWeatherOutside(currentWeather);
        } else {
//            System.out.println("No weather data available for the current time.");
        }
    }

}



