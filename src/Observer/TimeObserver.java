package src.Observer;


import src.Observer.Events.Event;
import src.Observer.Events.TimeEvent;
import src.logic.SimulationParameter;

import javax.swing.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class TimeObserver implements Observer{

    JLabel clockDisplay = null;

    public TimeObserver(){
        super();
    }
    public TimeObserver(JLabel clockDisplay){
        this.clockDisplay = clockDisplay;
    }

    @Override
    public void update(Event event){
//        System.out.println("time observer notified!");
        SimulationParameter param = ((TimeEvent)event).getParam();
        LocalTime currentTime = param.getTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        String timestamp = param.getDate().toString() + "," + formattedTime;
        if(clockDisplay != null){
            SwingUtilities.invokeLater(() -> {
                try {
                    clockDisplay.setText(formattedTime);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }
        if (param.getWeatherData().containsKey(timestamp)) {
            double currentWeather = (double) param.getWeatherData().get(timestamp);
            param.setWeatherOutside(currentWeather);
        } else {
//            System.out.println("No weather data available for the current time.");
        }
    }

}



