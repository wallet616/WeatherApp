package piotr.bartela;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Controller {
    public static JFrame frame;
    public static Settings settings;

    public static PanelMoveEventHandler panelMoveEventHandler;
    public static WeatherUpdater weatherUpdater;
    public static DataUpdater dataUpdater;

    public static MainPanel mainPanel;
    public static FiveDaysPanel fiveDaysPanel;


    public static void setPanel(JPanel panel) {
        frame.setContentPane(panel);
        frame.setVisible(true);
    }


    public static void main(String args[]) {
        System.setProperty("file.encoding", "UTF-8");

        settings = new Settings();
        settings.load();

        panelMoveEventHandler = new PanelMoveEventHandler();
        weatherUpdater = new WeatherUpdater();
        dataUpdater = new DataUpdater();

        frame = new JFrame("Pogoda");
        mainPanel = new MainPanel();
        fiveDaysPanel = new FiveDaysPanel();

        frame.setContentPane(mainPanel.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.pack();
        frame.setSize(540,376);
        frame.setLocation(settings.window_pos_x, settings.window_pos_y);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                settings.window_pos_x = frame.getX();
                settings.window_pos_y = frame.getY();

                settings.save();
            }
        });


        weatherUpdater.start();
        dataUpdater.start();
    }
}
