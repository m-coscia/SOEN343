package src.UI;

import src.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class firstFrame extends JFrame {
    private Controller controller = new Controller();
    private JFrame thisFrame;
    private LogInOrCreateFrame accountSelection;

    private JPanel mainPanel;

    private JLabel titleLabel;
    private JLabel layoutFileLabel;
    private JTextField layoutFileName;

    public firstFrame(){
        thisFrame = this;
        setContentPane(mainPanel);
        setTitle("Testing Swing UI Builder");
        getContentPane().setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
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
                    controller.layoutSetUp(layoutFileName.getText());


                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                LogInOrCreateFrame secondFrame = new LogInOrCreateFrame(thisFrame);
                secondFrame.setVisible(true);
                dispose();

//                setVisible(false);
//                //Font font = new Font();
//                accountSelection = new selectAccountGUI(mainPanel);
//                setContentPane(accountSelection.accountCreateForm);
//                //setSize(600,400);
//                setResizable(true);
//                setVisible(true);
////                setContentPane(dash.dashboard);
////                setSize(1400,900);
////                setResizable(true);
////                setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new firstFrame();

        
    }


}
