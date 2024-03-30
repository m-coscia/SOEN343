package src.UI;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import src.Controller;
import src.components.Room;
import src.logic.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class SimParameterGUI extends JFrame{
    private Controller controller = Controller.getController();
    JFrame previousFrame = null;
    JFrame thisFrame = this;
    private JPanel panel1;
    private JPanel mainZonePanel;

    private ArrayList<JCheckBox> zones = new ArrayList<>();
    private ArrayList<ArrayList<JCheckBox>> listOfZones = new ArrayList<>();
    private ArrayList<Double> temperatures = new ArrayList<>();
    private JTextField filenameField;
    private JPanel zoneCreationPanel;
    private JPanel buttonsPanel;
    private JButton addZoneButton;
    private JButton doneZoneButton;
    private JLabel timeLabel;
    private JSpinner tempSpinner;
    private JSpinner hourSpinner;
    private JSpinner minuteSpinner;
    private JSpinner spinner3;
    private JPanel testPanel;
    private JPanel dateAndtimePanel;
    private JButton doneButton;
    int zoneCount = 1;
    private JDatePickerImpl datePicker;

    private Profile profile;
    public SimParameterGUI(JFrame previousFrame, Profile profile){
        previousFrame = previousFrame;
        this.profile = profile;
        setUpButtons();

        zoneCreationPanel.setLayout(new GridLayout(0,controller.getRooms().size() + 3));

        //empty for spacing
        JLabel empty = new JLabel("");
        zoneCreationPanel.add(empty);

        //for every room type create a label for column
        for (Room r: controller.getRooms()){
            JLabel roomLabel = new JLabel(String.valueOf(r.getType()) + " (id: " + r.getId() +")");
            roomLabel.setHorizontalAlignment(SwingConstants.CENTER);
            zoneCreationPanel.add(roomLabel);
        }

        //for spacing
        JLabel empty1 = new JLabel("Temperature");
        empty1.setHorizontalAlignment(SwingConstants.CENTER);
        zoneCreationPanel.add(empty1);
        empty1.setVisible(true);

        //for spacing
        JLabel empty2 = new JLabel("");
        zoneCreationPanel.add(empty2);
        empty2.setVisible(false);


        JLabel zoneLabel = new JLabel("Zone 1");
        zoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
        zoneCreationPanel.add(zoneLabel);

        //add checkboxes for each room
        for (Room r: controller.getRooms()){
            JCheckBox c = new JCheckBox();
            zones.add(c);
            c.setHorizontalAlignment(SwingConstants.CENTER);
            zoneCreationPanel.add(c);
        }

        JTextField tempField = new JTextField();
        tempField.setHorizontalAlignment(SwingConstants.CENTER);
        zoneCreationPanel.add(tempField);

        //for spacing
        JLabel empty3 = new JLabel("");
        zoneCreationPanel.add(empty3);
        empty3.setVisible(false);
        //this button will add rows as needed
//        JButton addZoneButton = new JButton("Add Zone");
//        addZoneButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if(zoneCount < controller.getRooms().size()){
//                    addZones();
//                }else{
//                    System.out.println("cannot make more room then zones");
//                }
//
//            }
//        });
//        buttonsPanel.add(addZoneButton);

//        JButton doneZoneButton = new JButton("Done");
//        doneZoneButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                listOfZones.add(zones);
//                controller.setZones(listOfZones);
//                controller.setTemperatureFile(filenameField.getText());
//            }
//        });
//        buttonsPanel.add(doneZoneButton);

        mainZonePanel.add(zoneCreationPanel, BorderLayout.CENTER);
        add(panel1);
        //setSize(600,300);
        setUPDateSelection();
        pack();
        setTitle("Simulation Parameters");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setUpButtons(){
        addZoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (zoneCount < controller.getRooms().size()){
                    addZones();
                }else{
                    System.out.println("maximum number of zones created");
                }

            }
        });

        doneZoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listOfZones.add(zones);
                controller.setZones(listOfZones);
            }
        });

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                controller.setSimulationParams(filenameField.getText(), (Date) datePicker.getModel().getValue(), (int) hourSpinner.getModel().getValue(),
                        (int) hourSpinner.getModel().getValue(), (int) tempSpinner.getValue(), profile);

                Dashboard dash = new Dashboard(profile);
                dash.setVisible(true);
                dispose();
            }
        });
    }

    private void setUPDateSelection(){
        SpinnerNumberModel spinHour = new SpinnerNumberModel(0,0,23,1);
        SpinnerNumberModel spinMin = new SpinnerNumberModel(0,0,59,1);
        hourSpinner.setModel(spinHour);
        minuteSpinner.setModel(spinMin);


        UtilDateModel model = new UtilDateModel();
        model.setDate(2002,9,14);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        JLabel l22=new JLabel("DATE :");
        l22.setBounds(100,350,100,20);

        JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        add(datePicker, BorderLayout.SOUTH);


        testPanel.add(l22);

        datePicker.setBounds(220,350,120,30);
        testPanel.add(datePicker);
        dateAndtimePanel.add(testPanel);

    }
    private void addZones() {
        zoneCount++;
        listOfZones.add(zones);
        zones.clear();

        JLabel zoneLabel = new JLabel("Zone " + zoneCount);
        zoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
        zoneCreationPanel.add(zoneLabel);

        for (Room r : controller.getRooms()) {
            JCheckBox c = new JCheckBox();
            zones.add(c);
            c.setHorizontalAlignment(SwingConstants.CENTER);
            zoneCreationPanel.add(c);
        }
        JTextField tempField = new JTextField();
        tempField.setHorizontalAlignment(SwingConstants.CENTER);
        zoneCreationPanel.add(tempField);

        JLabel empty1 = new JLabel("");
        zoneCreationPanel.add(empty1);
        empty1.setVisible(false);

//        JLabel empty2 = new JLabel("");
//        zoneCreationPanel.add(empty2);
//        empty2.setVisible(false);

        validate();
        pack();
        repaint();
    }


    public static void main(String[] args) {
        new SimParameterGUI(null,null);
    }
}

class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}