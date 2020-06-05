public class Weather {
    private long temperature;
    private long humidity;
    private String clouds;
    private long windSpeed;
    private String name;

    public Weather(double temperature, double humidity, String clouds, double windSpeed, String name) {
        this.temperature = Math.round(temperature - 273);
        this.humidity = Math.round(humidity);
        this.clouds = clouds;
        this.windSpeed = Math.round(windSpeed);
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public long getTemperature() {
        return temperature;
    }

    public long getHumidity() {
        return humidity;
    }

    public String getClouds() {
        return clouds;
    }

    public long getWindSpeed() {
        return windSpeed;
    }
}
