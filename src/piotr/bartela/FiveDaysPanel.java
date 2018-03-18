package piotr.bartela;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FiveDaysPanel {
    public JPanel fiveDaysPanel;
    private JTextArea textArea1;
    private JButton back;
    private JLabel close;
    private JPanel content;


    public FiveDaysPanel() {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.setPanel(Controller.mainPanel.mainPanel);
            }
        });

        fiveDaysPanel.addMouseMotionListener(Controller.panelMoveEventHandler);
        fiveDaysPanel.addMouseListener(Controller.panelMoveEventHandler);
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });
    }

    public static double kelvinToCelsius(Double kelvin) {
        return kelvin - 273.15;
    }


    public void fill_list(JSONObject data) {
        try {
            System.out.println(data);

            content.removeAll();
            content.setLayout(new GridLayout(5, 1));

            JSONArray days = data.getJSONArray("list");


            for (int i = 1; i < 6; i++) {
                JSONObject o = days.getJSONObject(i);
                System.out.println(o);

                Calendar calender = Calendar.getInstance();
                calender.setTimeInMillis(o.getLong("dt") * 1000L);
                String time = new SimpleDateFormat("yyyy-MM-dd").format(calender.getTime());

                String image = o.getJSONArray("weather").getJSONObject(0).getString("icon");

                double pressure = o.getDouble("pressure");

                double temp_min = o.getJSONObject("temp").getDouble("min");
                double temp_max = o.getJSONObject("temp").getDouble("max");

                WeatherListElement e = new WeatherListElement();
                e.fill("/piotr/bartela/images/icons/" + image + ".png", time, pressure, kelvinToCelsius(temp_min), kelvinToCelsius(temp_max));
                content.add(e.mainPanel);
            }

            content.revalidate();
            content.repaint();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void setText(String text) {
        textArea1.setText(text);
    }
}
