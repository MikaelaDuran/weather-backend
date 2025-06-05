package org.example.weatherbackend.WeatherApp.Repo;

import org.example.weatherbackend.WeatherApp.Models.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepo extends JpaRepository<Weather, Integer> {
}
