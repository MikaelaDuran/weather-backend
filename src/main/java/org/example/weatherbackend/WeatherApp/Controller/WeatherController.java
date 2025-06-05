package org.example.weatherbackend.WeatherApp.Controller;

import org.example.weatherbackend.WeatherApp.Models.Weather;
import org.springframework.web.bind.annotation.*;
import org.example.weatherbackend.WeatherApp.Repo.WeatherRepo;
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
        return weatherRepo.save(weather);
    }

    @GetMapping("/{id}")
    public Weather getWeatherById(@PathVariable Integer id) {
        return weatherRepo.findById(id).orElse(null);
    }

    // Define endpoints here to handle HTTP requests
}
