package Controller;

import Models.Weather;
import Repo.WeatherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {

    @Autowired
    private WeatherRepo weatherRepo;

    public WeatherController(WeatherRepo repo) {
        this.weatherRepo = repo;
    }

    @GetMapping("/all")
    public List<Weather> getWeather() {
        return weatherRepo.findAll();
    }


    // Define endpoints here to handle HTTP requests
}
