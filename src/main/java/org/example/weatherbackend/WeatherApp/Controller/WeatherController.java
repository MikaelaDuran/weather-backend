package org.example.weatherbackend.WeatherApp.Controller;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;
import org.example.weatherbackend.WeatherApp.Models.Weather;
import org.example.weatherbackend.WeatherApp.Service.WeatherService;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/weather")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class WeatherController {

    private final WeatherService weatherService;
    private final ConcurrentHashMap<String, Bucket> buckets = new ConcurrentHashMap<>();

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    // 5 anrop per minut per IP-adress
    private Bucket resolveBucket(String ip) {
        return buckets.computeIfAbsent(ip, k -> {
            Refill refill = Refill.greedy(5, Duration.ofMinutes(1)); // 5 requests/minut
            Bandwidth limit = Bandwidth.classic(5, refill);
            return Bucket.builder().addLimit(limit).build();
        });
    }

    @GetMapping("/all")
    public Object getWeather(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        Bucket bucket = resolveBucket(ip);

        if (bucket.tryConsume(1)) {
            return weatherService.getAllWeather();
        } else {
            return "⛔ Du har nått gränsen för anrop. Vänta en stund och försök igen.";
        }
    }
}
