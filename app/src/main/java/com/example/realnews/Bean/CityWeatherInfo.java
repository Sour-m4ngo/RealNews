package com.example.realnews.Bean;

public class CityWeatherInfo {
    private String CityName;
    private String CurrentWeather;

    public CityWeatherInfo(String cityName, String currentWeather) {
        CityName = cityName;
        CurrentWeather = currentWeather;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getCurrentWeather() {
        return CurrentWeather;
    }

    public void setCurrentWeather(String currentWeather) {
        CurrentWeather = currentWeather;
    }
}
