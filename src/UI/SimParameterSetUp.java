package src.UI;

import src.Controller;
import src.components.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SimParameterSetUp extends JFrame{
    private Controller controller = new Controller();
    private JPanel panel1;
    private JPanel zoneCreationPanel;

    private ArrayList<JCheckBox> zones = new ArrayList<>();
    private ArrayList<ArrayList<JCheckBox>> listOfZones = new ArrayList<>();
    private JTextField textField1;
    int zoneCount = 1;

    public SimParameterSetUp(){
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

        //for spacing
        JLabel empty1 = new JLabel("Temperature");
        zoneCreationPanel.add(empty1);
        empty1.setVisible(false);

        //for spacing
        JLabel empty2 = new JLabel("");
        zoneCreationPanel.add(empty2);
        empty2.setVisible(false);

        //this button will add rows as needed
        JButton addZoneButton = new JButton("Add Zone");
        addZoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(zoneCount < controller.getRooms().size()){
                    addZones();
                }else{
                    System.out.println("cannot make more room then zones");
                }

            }
        });
        zoneCreationPanel.add(addZoneButton);

        JButton doneButton = new JButton("Done");
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listOfZones.add(zones);
                controller.setZones(listOfZones);
            }
        });
        zoneCreationPanel.add(doneButton);

        add(panel1);
        //setSize(600,300);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
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

        JLabel empty1 = new JLabel("");
        zoneCreationPanel.add(empty1);
        empty1.setVisible(false);

        JLabel empty2 = new JLabel("");
        zoneCreationPanel.add(empty2);
        empty2.setVisible(false);

        validate();
        pack();
        repaint();
    }
    public static void main(String[] args) {
        new SimParameterSetUp();
    }
}
