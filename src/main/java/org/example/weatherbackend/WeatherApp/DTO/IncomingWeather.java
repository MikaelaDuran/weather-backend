package org.example.weatherbackend.WeatherApp.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

// Matchar MQTT JSON-strukturen
@Getter
@Setter
public class IncomingWeather {

    private double temperature;
    private double pressure;
    private double humidity;

    }
