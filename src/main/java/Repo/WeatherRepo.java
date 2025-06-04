package Repo;

import Models.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepo extends JpaRepository<Weather, Integer> {
}
