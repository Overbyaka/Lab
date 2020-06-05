import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class URLConnectionReader extends Thread{
    private final static String key = "&appid=794fd93fd7e0c10ee376d9e89b082808";
    private final static String baseURL = "http://api.openweathermap.org/data/2.5/forecast?q=";
    private final static String lang = "&lang=ru";

    private String city;
    private String country;
    private WeatherSetter weatherSetter;

    private String urlString;

    URLConnectionReader(String city, WeatherSetter weatherSetter){
        this.city = city;
        this.weatherSetter = weatherSetter;

        this.urlString = baseURL + city + key + lang;
    }

    URLConnectionReader(String city, String country, WeatherSetter weatherSetter){
        this.city = city;
        this.country = country;
        this.weatherSetter = weatherSetter;

        this.urlString = baseURL + city + "," + country + key + lang;
    }

    @Override
    public void run() {
        weatherSetter.setResult(getWeather(city, country));
    }

    public Weather getWeather(String city, String country){
        Weather weather = null;

        try {
            URL openWeather = new URL(urlString);
            URLConnection urlConnection = openWeather.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));

            String jsonLine = "";
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                jsonLine += inputLine;
            in.close();

            JSONObject jsonObject = new JSONObject(jsonLine);
            JSONArray list = (JSONArray)jsonObject.get("list");

            String name = jsonObject.getJSONObject("city").getString("name");

            JSONObject item = (JSONObject)list.get(0);

            JSONObject main = (JSONObject)item.get("main");
            JSONArray weatherJSONArray = (JSONArray) item.get("weather");
            JSONObject clouds = weatherJSONArray.getJSONObject(0);
            JSONObject wind = (JSONObject) item.get("wind");

            weather = new Weather(main.getDouble("temp"), main.getDouble("humidity"),
                    clouds.getString("description"), wind.getDouble("speed"), name);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }

        return weather;
    }
}
