package piotr.bartela;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class WeatherListElement {
    public JPanel mainPanel;
    private JLabel icon;
    private JLabel date;
    private JLabel pressure;
    private JLabel temp;

    public void fill(String image_url, String date, double pressure, double temp_min, double temp_max) {
        this.date.setText(date);

        String p = new DecimalFormat("####.#").format(pressure);
        this.pressure.setText("Ciśnienie: " + p + " hPa");

        String tm = new DecimalFormat("###.#").format(temp_min);
        tm = tm + "°C";

        String tx = new DecimalFormat("###.#").format(temp_max);
        tx = tx + "°C";

        this.temp.setText(tm + " / " + tx);

        displayImage(icon, image_url);
    }

    private void displayImage(JLabel label, String url) {
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource(url)).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        label.setText("");
        label.setIcon(imageIcon);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
