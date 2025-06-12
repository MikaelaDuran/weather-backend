package org.example.weatherbackend.WeatherApp.Controller;

import org.example.weatherbackend.WeatherApp.Models.Weather;
import org.example.weatherbackend.WeatherApp.Service.WeatherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
@CrossOrigin(origins = "http://localhost:3000")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/all")
    public List<Weather> getWeather() {
        return weatherService.getAllWeather();
    }

    @PostMapping("/add")
    public Weather addWeather(@RequestBody Weather weather) {
        return weatherService.saveWeather(weather);
    }

    @GetMapping("/{id}")
    public Weather getWeatherById(@PathVariable int id) {
        return weatherService.getWeatherById(id);
    }

    @PostMapping("/update/{id}")
    public Weather updateWeather(@PathVariable int id, @RequestBody Weather weather) {
        return weatherService.updateWeather(id, weather);
    }
}
