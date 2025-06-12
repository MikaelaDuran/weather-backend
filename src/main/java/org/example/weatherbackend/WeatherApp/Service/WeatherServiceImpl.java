package org.example.weatherbackend.WeatherApp.Service;

import org.example.weatherbackend.WeatherApp.Models.Weather;
import org.example.weatherbackend.WeatherApp.Repo.WeatherRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepo weatherRepo;

    public WeatherServiceImpl(WeatherRepo weatherRepo) {
        this.weatherRepo = weatherRepo;
    }

    @Override
    public List<Weather> getAllWeather() {
        return weatherRepo.findAll();
    }

    @Override
    public Weather saveWeather(Weather weather) {
        weather.setTimestamp(LocalDateTime.now()); // Sätt timestamp
        return weatherRepo.save(weather);
    }

    @Override
    public Weather getWeatherById(int id) {
        return weatherRepo.findById(id).orElse(null);
    }

    @Override
    public Weather updateWeather(int id, Weather weather) {
        Weather existing = weatherRepo.findById(id).orElse(null);
        if (existing != null) {
            existing.setTemp(weather.getTemp());
            existing.setPressure(weather.getPressure());
            existing.setHumidity(weather.getHumidity());
            existing.setTimestamp(LocalDateTime.now());
            return weatherRepo.save(existing);
        }
        return null;
    }
}
