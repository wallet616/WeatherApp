package piotr.bartela;

import org.json.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Settings {

    public String settings_file_path = "/weather/settings.json";
    public String location = "";
    public int window_pos_x = 100;
    public int window_pos_y = 100;

    public String getLocation() {
        return location;
    }
    public int getWindow_pos_x() {
        return window_pos_x;
    }
    public int getWindow_pos_y() {
        return window_pos_y;
    }



    public Settings() {
        String OS = (System.getProperty("os.name")).toUpperCase();
        if (OS.contains("WIN"))
        {
            settings_file_path = System.getenv("AppData") + settings_file_path;
        }
        else
        {
            settings_file_path = System.getProperty("user.home") + settings_file_path;
        }
    }

    public void save() {
        JSONObject jo_settings = new JSONObject(this);
        String js_settings = jo_settings.toString();

        try {
            File file = new File(settings_file_path);
            file.getParentFile().mkdirs();
            file.createNewFile();

            Writer out_stream = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            out_stream.write(js_settings);
            out_stream.flush();
            out_stream.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void load() {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(settings_file_path));
            String js_settings = new String(encoded, "UTF-8");
            JSONObject jo_settings = new JSONObject(js_settings);

            location = jo_settings.getString("location");
            window_pos_x = jo_settings.getInt("window_pos_x");
            window_pos_y = jo_settings.getInt("window_pos_y");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
