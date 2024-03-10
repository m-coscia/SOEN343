package UI;

import logic.HouseLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class firstFrame extends JFrame {
    private DashboardGUI dash = new DashboardGUI();

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel layoutFileLabel;
    private JTextField layoutFileName;

    public firstFrame(){
        setContentPane(mainPanel);
        setTitle("Testing Swing UI Builder");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        layoutFileName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("enter key was pressed");
                //TODO: can this be done using command pattern?
                try {
                    System.out.println(layoutFileName.getText());
                    HouseLayout.getHouseLayout().setHouseLayout(layoutFileName.getText());

                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                setVisible(false);
                setContentPane(dash.dashboard);
                setSize(1400,900);
                setResizable(true);
                setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new firstFrame();
    }


}
