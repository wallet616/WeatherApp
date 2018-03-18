package piotr.bartela;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class MainPanel {
    public JPanel mainPanel;
    public JPanel content;
    private JTextField location;
    public JLabel today_is;
    private JLabel temp;
    private JLabel humidity;
    private JLabel temp_min;
    private JLabel temp_max;
    private JLabel wind;
    private JLabel rain;
    private JLabel clouds;
    private JButton five_days_button;
    private JLabel close;
    private JLabel pressure;
    public JPanel image;


    public MainPanel() {
        location.setText(Controller.settings.location);


        five_days_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                FiveDaysPanel new_panel = new FiveDaysPanel();
                new_panel.setText("button1");*/

                Controller.setPanel(Controller.fiveDaysPanel.fiveDaysPanel);
            }
        });

        location.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.settings.location = location.getText().trim();
                Controller.settings.save();

                Controller.weatherUpdater.update_in(0);
            }
        });

        location.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyChar() != '\n') {
                    Controller.settings.location = location.getText().trim();
                    Controller.settings.save();

                    Controller.weatherUpdater.update_in(2000);
                }
            }
        });

        mainPanel.addMouseMotionListener(Controller.panelMoveEventHandler);
        mainPanel.addMouseListener(Controller.panelMoveEventHandler);
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });
    }

    public static double fahrenheitToCelsius(Double fahrenheit) {
        return (fahrenheit - 32.0) / 1.8;
    }

    public static double kelvinToCelsius(Double kelvin) {
        return kelvin - 273.15;
    }

    public void update_with_data(JSONObject data) {
        try {
            System.out.println(data);

            String t = new DecimalFormat("###.#").format(kelvinToCelsius(data.getJSONObject("main").getDouble("temp")));
            temp.setText("Temperatura: " + t + " °C");

            String tm = new DecimalFormat("###.#").format(kelvinToCelsius(data.getJSONObject("main").getDouble("temp_min")));
            temp_min.setText("Min: " + tm + " °C");

            String tx = new DecimalFormat("###.#").format(kelvinToCelsius(data.getJSONObject("main").getDouble("temp_max")));
            temp_max.setText("Min: " + tx + " °C");

            humidity.setText("Wilgotność: " + Double.toString(data.getJSONObject("main").getDouble("humidity")) + "%");

            wind.setText("Wiatr: " + Double.toString(data.getJSONObject("wind").getDouble("speed")) + " km/h");

            clouds.setText("Zachmurzenie: " + Double.toString(data.getJSONObject("clouds").getDouble("all")) + " %");

            String p = new DecimalFormat("####.#").format(data.getJSONObject("main").getDouble("pressure"));
            pressure.setText("Ciśnienie: " + p + " hPa");

            String icon = data.getJSONArray("weather").getJSONObject(0).getString("icon");
            displayImage(image, "/piotr/bartela/images/icons/" + icon + ".png");

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    private void displayImage(JPanel jp, String url) {
        JLabel jl = new JLabel("", JLabel.CENTER);
        jp.setLayout(new BorderLayout());
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource(url)).getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH));
        jl.setIcon(imageIcon);
        jp.add(jl, BorderLayout.CENTER);

        jp.revalidate();
        jp.repaint();
    }
}
