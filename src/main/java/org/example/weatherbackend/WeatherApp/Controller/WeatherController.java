package org.example.weatherbackend.WeatherApp.Controller;

import org.example.weatherbackend.WeatherApp.Models.Weather;
import org.springframework.web.bind.annotation.*;
import org.example.weatherbackend.WeatherApp.Repo.WeatherRepo;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/weather")
@CrossOrigin(origins = "http://localhost:3000")
public class WeatherController {


    private WeatherRepo weatherRepo;

    public WeatherController(WeatherRepo repo) {
        this.weatherRepo = repo;
    }

    @GetMapping("/all")
    public List<Weather> getWeather() {
        return weatherRepo.findAll();
    }

    @PostMapping("/add")
    public Weather addWeather(@RequestBody Weather weather) {
        weather.setTimestamp(LocalDateTime.now()); //Om man vill lägga till egen data
        return weatherRepo.save(weather);
    }

    @GetMapping("/{id}")
    public Weather getWeatherById(@PathVariable Integer id) {
        return weatherRepo.findById(id).orElse(null);
    }

    @PostMapping("/update/{id}")
    public Weather updateWeather(@PathVariable Integer id, @RequestBody Weather weather) {
        Weather existingWeather = weatherRepo.findById(id).orElse(null);
        if (existingWeather != null) {
            existingWeather.setTemp(weather.getTemp());
            existingWeather.setPressure(weather.getPressure());
            existingWeather.setHumidity(weather.getHumidity());
            existingWeather.setTimestamp(LocalDateTime.now()); // Uppdatera timestamp
            return weatherRepo.save(existingWeather);
        }
        return null; // Eller hantera fel på annat sätt
    }

}
