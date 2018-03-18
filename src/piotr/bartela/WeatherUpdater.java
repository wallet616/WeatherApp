package piotr.bartela;

import org.json.JSONObject;

public class WeatherUpdater extends Thread {
    public static final int update_weather_later = 600000; // 10 minutes to ms
    public static final String url = "http://api.openweathermap.org/data/2.5/";
    //protected static final String apiKEY = "appid=80ff6ed695fd4043e94b26a3b35eb546";
    protected static final String apiKEY = "appid=410463b3935acea56c8171825dbb4440";

    public int timer = 0;
    public boolean is_running = true;

    @Override
    public void run(){
        while (is_running) {
            try {
                timer -= 100;
                if (timer < 0) {
                    update_in(update_weather_later);

                    String city_query = "q=" + Controller.settings.location.replaceAll("\\s+", "_");
                    String current_weather = MakeRequest.executeGet(url + "weather", new String[]{apiKEY, city_query});
                    String five_days_weather = MakeRequest.executeGet(url + "forecast/daily", new String[]{apiKEY, city_query});
                    Controller.mainPanel.update_with_data(new JSONObject(current_weather));
                    Controller.fiveDaysPanel.fill_list(new JSONObject(five_days_weather));

                }

                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


    public void update_in(int time) {
        timer = time;
    }
}
