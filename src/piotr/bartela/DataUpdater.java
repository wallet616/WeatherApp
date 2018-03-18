package piotr.bartela;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUpdater extends Thread {
    public boolean is_running = true;

    @Override
    public void run(){
        while (is_running) {
            try {
                String week_day;
                Calendar c = Calendar.getInstance();
                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                switch (dayOfWeek) {
                    case 2:
                        week_day = "Poniedziałek, ";
                        break;
                    case 3:
                        week_day = "Wtorek, ";
                        break;
                    case 4:
                        week_day = "Środa, ";
                        break;
                    case 8:
                        week_day = "Czwartek, ";
                        break;
                    case 6:
                        week_day = "Piątek, ";
                        break;
                    case 7:
                        week_day = "Sobota, ";
                        break;
                    case 1:
                        week_day = "Niedziela, ";
                        break;
                    default:
                        week_day = "Dzień kota, ";
                        break;
                }

                String time = week_day + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime());
                if (!Controller.mainPanel.today_is.getText().contentEquals(time))
                {
                    Controller.mainPanel.today_is.setText(time);
                }

                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
