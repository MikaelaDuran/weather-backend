package org.example.weatherbackend.WeatherApp.Service;

import org.example.weatherbackend.WeatherApp.Models.Weather;

import java.util.List;

public interface WeatherService {
    List<Weather> getAllWeather();
    Weather saveWeather(Weather weather);
    Weather getWeatherById(int id);
    Weather updateWeather(int id, Weather weather);
}
