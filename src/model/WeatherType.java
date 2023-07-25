package model;

public enum WeatherType {
    CLOUDY(0),
    SUNNY(1),
    WINDY(2),
    STORMY(3),
    PARTLY_CLOUDY(4),
    RAINY(5);

    final int weatherNumber;

    WeatherType(int weatherNumber) {
        this.weatherNumber = weatherNumber;
    }

    public int getWeatherNumber() {
        return weatherNumber;
    }

}
